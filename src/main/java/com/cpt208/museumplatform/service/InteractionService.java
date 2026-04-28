package com.cpt208.museumplatform.service;

import com.cpt208.museumplatform.dto.PuzzleFinishResponse;
import com.cpt208.museumplatform.dto.PuzzleGameResponse;
import com.cpt208.museumplatform.entity.ArtifactEntity;
import com.cpt208.museumplatform.entity.UserArtifactRecordEntity;
import com.cpt208.museumplatform.entity.UserFavoriteEntity;
import com.cpt208.museumplatform.model.ArtifactDetail;
import com.cpt208.museumplatform.model.ArtifactRecord;
import com.cpt208.museumplatform.repository.ArtifactRepository;
import com.cpt208.museumplatform.repository.UserArtifactRecordRepository;
import com.cpt208.museumplatform.repository.UserFavoriteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InteractionService {

    private final ArtifactRepository artifactRepository;
    private final DeepSeekService deepSeekService;
    private final UserFavoriteRepository userFavoriteRepository;
    private final UserArtifactRecordRepository userArtifactRecordRepository;
    private final Map<Long, Long> currentTurtleArtifactIdByUser = new ConcurrentHashMap<>();
    private final Map<String, String> currentTurtleQuestionByUser = new ConcurrentHashMap<>();
    private final Map<Long, Long> currentPuzzleArtifactIdByUser = new ConcurrentHashMap<>();
    private final Map<Long, Long> currentOfflineArtifactIdByUser = new ConcurrentHashMap<>();

    public InteractionService(
        ArtifactRepository artifactRepository,
        DeepSeekService deepSeekService,
        UserFavoriteRepository userFavoriteRepository,
        UserArtifactRecordRepository userArtifactRecordRepository
    ) {
        this.artifactRepository = artifactRepository;
        this.deepSeekService = deepSeekService;
        this.userFavoriteRepository = userFavoriteRepository;
        this.userArtifactRecordRepository = userArtifactRecordRepository;
    }

    public TurtleScenario getCurrentTurtleScenario(Long userId, String lang) {
        ArtifactEntity artifact = getOrAssignCurrentTurtleArtifact(userId);
        String question = currentTurtleQuestionByUser.computeIfAbsent(
            userId + ":" + normalizeLang(lang),
            ignored -> {
                String artifactQuestion = artifact.getTurtleQuestionByLanguage(normalizeLang(lang));
                if (artifactQuestion != null && !artifactQuestion.isBlank()) {
                    return artifactQuestion.trim();
                }
                return deepSeekService.createTurtleQuestion(
                    artifact.getTitleByLanguage(normalizeLang(lang)),
                    artifact.getBackgroundStoryByLanguage(normalizeLang(lang))
                );
            }
        );
        return new TurtleScenario("Turtle Soup", question, toDetail(userId, artifact, lang));
    }

    public void moveToNextTurtleScenario(Long userId) {
        currentTurtleArtifactIdByUser.remove(userId);
        currentTurtleQuestionByUser.keySet().removeIf(key -> key.startsWith(userId + ":"));
    }

    public TurtleAnswerResult submitTurtleAnswer(Long userId, String answer, String lang) {
        String normalized = answer == null ? "" : answer.trim().toLowerCase();
        if (normalized.isBlank()) {
            throw new IllegalArgumentException("Please enter your answer.");
        }

        ArtifactEntity artifact = getOrAssignCurrentTurtleArtifact(userId);
        boolean correct = normalized.contains(artifact.getTitle().toLowerCase())
            || (artifact.getTitleZh() != null && normalized.contains(artifact.getTitleZh().toLowerCase()));
        if (!correct) {
            return new TurtleAnswerResult(false, "no", null);
        }

        saveRecordIfAbsent(userId, artifact.getId(), "turtle");
        return new TurtleAnswerResult(true, "Correct answer.", toDetail(userId, artifact, lang));
    }

    public ArtifactDetail revealCurrentTurtleArtifact(Long userId, String lang) {
        ArtifactEntity artifact = getOrAssignCurrentTurtleArtifact(userId);
        saveRecordIfAbsent(userId, artifact.getId(), "turtle");
        return toDetail(userId, artifact, lang);
    }

    public PuzzleGameResponse getPuzzleGame(Long userId, String lang) {
        ArtifactEntity artifact = getOrAssignCurrentPuzzleArtifact(userId);
        List<Integer> order = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8));
        Collections.shuffle(order);
        return new PuzzleGameResponse(order, toDetail(userId, artifact, lang));
    }

    public ArtifactDetail getCurrentPuzzleArtifact(Long userId, String lang) {
        return toDetail(userId, getOrAssignCurrentPuzzleArtifact(userId), lang);
    }

    public void moveToNextPuzzleArtifact(Long userId) {
        currentPuzzleArtifactIdByUser.remove(userId);
    }

    public Integer choosePuzzleHint(List<Integer> placedPieceIds) {
        Set<Integer> placed = placedPieceIds == null ? Set.of() : new LinkedHashSet<>(placedPieceIds);
        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < 9; i += 1) {
            if (!placed.contains(i)) {
                remaining.add(i);
            }
        }
        if (remaining.isEmpty()) {
            return null;
        }
        Collections.shuffle(remaining);
        return remaining.getFirst();
    }

    public PuzzleFinishResponse finishPuzzle(Long userId, List<Integer> placedPieceIds, String lang) {
        Set<Integer> placed = placedPieceIds == null ? Set.of() : new LinkedHashSet<>(placedPieceIds);
        boolean complete = placed.size() == 9;
        if (!complete) {
            return new PuzzleFinishResponse(false, "The puzzle is not complete yet.", null);
        }

        ArtifactEntity artifact = getOrAssignCurrentPuzzleArtifact(userId);
        saveRecordIfAbsent(userId, artifact.getId(), "puzzle");
        return new PuzzleFinishResponse(true, "Puzzle completed.", toDetail(userId, artifact, lang));
    }

    public List<ArtifactDetail> getOfflineArtifacts(Long userId, String lang) {
        ArtifactEntity scannedArtifact = assignRandomArtifactToOfflineScan(userId);
        List<ArtifactEntity> candidates = artifactRepository.findByCategoryIgnoreCase(scannedArtifact.getCategory());
        List<ArtifactDetail> results = new ArrayList<>();
        for (ArtifactEntity candidate : candidates) {
            results.add(toDetail(userId, candidate, lang));
        }
        if (results.size() < 2) {
            for (ArtifactEntity artifact : artifactRepository.findAll()) {
                if (results.stream().noneMatch(item -> item.getId().equals(artifact.getId()))) {
                    results.add(toDetail(userId, artifact, lang));
                }
                if (results.size() >= 3) {
                    break;
                }
            }
        }
        return results;
    }

    public String askOfflineAssistant(Long userId, String question) {
        String prompt = question == null ? "" : question.trim();
        if (prompt.isBlank()) {
            throw new IllegalArgumentException("Please enter your question.");
        }
        ArtifactEntity artifact = getOrAssignOfflineArtifact(userId);
        return deepSeekService.askArtifactAssistant(artifact.getTitle(), artifact.getDescription(), prompt);
    }

    public FavoriteToggleResponseData toggleFavorite(Long userId, Long artifactId) {
        ArtifactEntity artifact = artifactRepository.findById(artifactId)
            .orElseThrow(() -> new IllegalArgumentException("Artifact not found."));

        boolean active;
        if (userFavoriteRepository.existsByUserIdAndArtifactId(userId, artifact.getId())) {
            userFavoriteRepository.deleteByUserIdAndArtifactId(userId, artifact.getId());
            active = false;
        } else {
            userFavoriteRepository.save(new UserFavoriteEntity(userId, artifact.getId(), LocalDateTime.now()));
            active = true;
        }
        return new FavoriteToggleResponseData(active, active ? "Saved. View it in My Profile - Favorites." : "Removed from favorites.");
    }

    public List<ArtifactRecord> getRecords(Long userId, String type, String lang) {
        return switch (type) {
            case "puzzle" -> buildHistoryRecords(userId, "puzzle", lang);
            case "turtle" -> buildHistoryRecords(userId, "turtle", lang);
            case "favorites" -> buildFavoriteRecords(userId, lang);
            default -> List.of();
        };
    }

    public boolean isFavorite(Long userId, Long artifactId) {
        return userFavoriteRepository.existsByUserIdAndArtifactId(userId, artifactId);
    }

    private List<ArtifactRecord> buildHistoryRecords(Long userId, String type, String lang) {
        List<ArtifactRecord> records = new ArrayList<>();
        for (UserArtifactRecordEntity record : userArtifactRecordRepository.findByUserIdAndRecordTypeOrderByCreatedAtDesc(userId, type)) {
            artifactRepository.findById(record.getArtifactId()).ifPresent(artifact -> records.add(
                new ArtifactRecord(
                    artifact.getId(),
                    type,
                    artifact.getTitleByLanguage(normalizeLang(lang)),
                    artifact.getImageLabelByLanguage(normalizeLang(lang)),
                    artifact.getImageUrl(),
                    artifact.getDescriptionByLanguage(normalizeLang(lang)),
                    isFavorite(userId, artifact.getId())
                )
            ));
        }
        return records;
    }

    private List<ArtifactRecord> buildFavoriteRecords(Long userId, String lang) {
        List<ArtifactRecord> records = new ArrayList<>();
        for (UserFavoriteEntity favorite : userFavoriteRepository.findByUserIdOrderByCreatedAtDesc(userId)) {
            artifactRepository.findById(favorite.getArtifactId()).ifPresent(artifact -> records.add(
                new ArtifactRecord(
                    artifact.getId(),
                    "favorites",
                    artifact.getTitleByLanguage(normalizeLang(lang)),
                    artifact.getImageLabelByLanguage(normalizeLang(lang)),
                    artifact.getImageUrl(),
                    artifact.getDescriptionByLanguage(normalizeLang(lang)),
                    true
                )
            ));
        }
        return records;
    }

    private ArtifactEntity getOrAssignCurrentTurtleArtifact(Long userId) {
        Long artifactId = currentTurtleArtifactIdByUser.computeIfAbsent(userId, ignored -> chooseRandomArtifact().getId());
        return artifactRepository.findById(artifactId).orElseGet(this::chooseRandomArtifact);
    }

    private ArtifactEntity getOrAssignCurrentPuzzleArtifact(Long userId) {
        Long artifactId = currentPuzzleArtifactIdByUser.computeIfAbsent(userId, ignored -> chooseRandomArtifact().getId());
        return artifactRepository.findById(artifactId).orElseGet(this::chooseRandomArtifact);
    }

    private ArtifactEntity assignRandomArtifactToOfflineScan(Long userId) {
        ArtifactEntity artifact = chooseRandomArtifact();
        currentOfflineArtifactIdByUser.put(userId, artifact.getId());
        return artifact;
    }

    private ArtifactEntity getOrAssignOfflineArtifact(Long userId) {
        Long artifactId = currentOfflineArtifactIdByUser.computeIfAbsent(userId, ignored -> chooseRandomArtifact().getId());
        return artifactRepository.findById(artifactId).orElseGet(this::chooseRandomArtifact);
    }

    private ArtifactEntity chooseRandomArtifact() {
        List<ArtifactEntity> artifacts = artifactRepository.findAll();
        if (artifacts.isEmpty()) {
            throw new IllegalStateException("No artifacts are available.");
        }
        Collections.shuffle(artifacts);
        return artifacts.getFirst();
    }

    private void saveRecordIfAbsent(Long userId, Long artifactId, String recordType) {
        if (userArtifactRecordRepository.findByUserIdAndArtifactIdAndRecordType(userId, artifactId, recordType).isEmpty()) {
            userArtifactRecordRepository.save(new UserArtifactRecordEntity(userId, artifactId, recordType, LocalDateTime.now()));
        }
    }

    private ArtifactDetail toDetail(Long userId, ArtifactEntity artifact, String lang) {
        return new ArtifactDetail(
            artifact.getId(),
            artifact.getTitleByLanguage(normalizeLang(lang)),
            artifact.getImageLabelByLanguage(normalizeLang(lang)),
            artifact.getImageUrl(),
            artifact.getThumbnailUrl(),
            artifact.getDescriptionByLanguage(normalizeLang(lang)),
            isFavorite(userId, artifact.getId())
        );
    }

    private String normalizeLang(String lang) {
        return "zh".equalsIgnoreCase(lang) ? "zh" : "en";
    }

    public record TurtleScenario(String title, String helperText, ArtifactDetail artifact) {
    }

    public record TurtleAnswerResult(boolean correct, String message, ArtifactDetail artifact) {
    }

    public record FavoriteToggleResponseData(boolean active, String message) {
    }
}

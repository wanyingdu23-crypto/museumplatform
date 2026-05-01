package com.cpt208.museumplatform.service;

import com.cpt208.museumplatform.dto.PuzzleFinishResponse;
import com.cpt208.museumplatform.dto.PuzzleGameResponse;
import com.cpt208.museumplatform.entity.ArtifactEntity;
import com.cpt208.museumplatform.entity.PuzzleArtifactEntity;
import com.cpt208.museumplatform.entity.UserArtifactRecordEntity;
import com.cpt208.museumplatform.entity.UserFavoriteEntity;
import com.cpt208.museumplatform.model.ArtifactDetail;
import com.cpt208.museumplatform.model.ArtifactRecord;
import com.cpt208.museumplatform.repository.ArtifactRepository;
import com.cpt208.museumplatform.repository.PuzzleArtifactRepository;
import com.cpt208.museumplatform.repository.UserArtifactRecordRepository;
import com.cpt208.museumplatform.repository.UserFavoriteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class InteractionService {

    private final ArtifactRepository artifactRepository;
    private final PuzzleArtifactRepository puzzleArtifactRepository;
    private final DeepSeekService deepSeekService;
    private final UserFavoriteRepository userFavoriteRepository;
    private final UserArtifactRecordRepository userArtifactRecordRepository;
    private final Map<Long, Long> currentTurtleArtifactIdByUser = new ConcurrentHashMap<>();
    private final Map<String, String> currentTurtleQuestionByUser = new ConcurrentHashMap<>();
    private final Map<Long, Long> currentPuzzleArtifactIdByUser = new ConcurrentHashMap<>();
    private final Map<Long, Long> currentOfflineArtifactIdByUser = new ConcurrentHashMap<>();

    public InteractionService(
        ArtifactRepository artifactRepository,
        PuzzleArtifactRepository puzzleArtifactRepository,
        DeepSeekService deepSeekService,
        UserFavoriteRepository userFavoriteRepository,
        UserArtifactRecordRepository userArtifactRecordRepository
    ) {
        this.artifactRepository = artifactRepository;
        this.puzzleArtifactRepository = puzzleArtifactRepository;
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
        boolean correct = matchesArtifactNameOrDescription(artifact, normalized, lang);
        if (!correct) {
            return new TurtleAnswerResult(false, "no", null);
        }

        saveRecordIfAbsent(userId, artifact.getId(), "turtle");
        return new TurtleAnswerResult(true, "yes", toDetail(userId, artifact, lang));
    }

    public ArtifactDetail revealCurrentTurtleArtifact(Long userId, String lang) {
        ArtifactEntity artifact = getOrAssignCurrentTurtleArtifact(userId);
        saveRecordIfAbsent(userId, artifact.getId(), "turtle");
        return toDetail(userId, artifact, lang);
    }

    public PuzzleGameResponse getPuzzleGame(Long userId, String lang) {
        PuzzleArtifactEntity artifact = getOrAssignCurrentPuzzleArtifact(userId);
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

        PuzzleArtifactEntity artifact = getOrAssignCurrentPuzzleArtifact(userId);
        saveRecordIfAbsent(userId, artifact.getId(), "puzzle");
        return new PuzzleFinishResponse(true, "Puzzle completed.", toDetail(userId, artifact, lang));
    }

    public List<ArtifactDetail> getOfflineArtifacts(Long userId, String lang) {
        ArtifactPayload scannedArtifact = assignRandomArtifactToOfflineScan(userId, lang);
        List<ArtifactEntity> candidates = artifactRepository.findByCategoryIgnoreCase(scannedArtifact.category());
        List<ArtifactDetail> results = new ArrayList<>();
        for (ArtifactEntity candidate : candidates) {
            results.add(toDetail(userId, candidate, lang));
        }
        for (PuzzleArtifactEntity candidate : puzzleArtifactRepository.findByCategoryIgnoreCase(scannedArtifact.category())) {
            if (results.stream().noneMatch(item -> item.getId().equals(candidate.getId()))) {
                results.add(toDetail(userId, candidate, lang));
            }
        }
        if (results.size() < 2) {
            for (ArtifactEntity artifact : artifactRepository.findAll()) {
                if (results.stream().noneMatch(item -> item.getId().equals(artifact.getId()))) {
                    results.add(toDetail(userId, artifact, lang));
                }
                if (results.size() >= 4) {
                    break;
                }
            }
        }
        if (results.size() < 2) {
            for (PuzzleArtifactEntity artifact : puzzleArtifactRepository.findAll()) {
                if (results.stream().noneMatch(item -> item.getId().equals(artifact.getId()))) {
                    results.add(toDetail(userId, artifact, lang));
                }
                if (results.size() >= 4) {
                    break;
                }
            }
        }
        return results;
    }

    public List<ArtifactDetail> getCatalogArtifacts(Long userId, String lang, String query) {
        String normalizedLang = normalizeLang(lang);
        String keyword = query == null ? "" : query.trim().toLowerCase();
        List<ArtifactDetail> results = new ArrayList<>();

        for (ArtifactEntity artifact : artifactRepository.findAll()) {
            ArtifactDetail detail = toDetail(userId, artifact, normalizedLang);
            if (matchesCatalogQuery(detail, keyword)) {
                results.add(detail);
            }
        }

        for (PuzzleArtifactEntity artifact : puzzleArtifactRepository.findAll()) {
            ArtifactDetail detail = toDetail(userId, artifact, normalizedLang);
            if (matchesCatalogQuery(detail, keyword)) {
                results.add(detail);
            }
        }

        results.sort((left, right) -> left.getTitle().compareToIgnoreCase(right.getTitle()));
        return results;
    }

    public String askOfflineAssistant(Long userId, String question) {
        String prompt = question == null ? "" : question.trim();
        if (prompt.isBlank()) {
            throw new IllegalArgumentException("Please enter your question.");
        }
        ArtifactPayload artifact = getOrAssignOfflineArtifact(userId);
        return deepSeekService.askArtifactAssistant(artifact.title(), artifact.description(), prompt);
    }

    @Transactional
    public FavoriteToggleResponseData toggleFavorite(Long userId, Long artifactId) {
        ArtifactPayload artifact = findArtifactPayload(artifactId, "en")
            .orElseThrow(() -> new IllegalArgumentException("Artifact not found."));

        boolean active;
        if (userFavoriteRepository.findByUserIdAndArtifactId(userId, artifact.id()).isPresent()) {
            userFavoriteRepository.deleteByUserIdAndArtifactId(userId, artifact.id());
            active = false;
        } else {
            userFavoriteRepository.save(new UserFavoriteEntity(userId, artifact.id(), LocalDateTime.now()));
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
            findArtifactPayload(record.getArtifactId(), lang).ifPresent(artifact -> records.add(
                new ArtifactRecord(
                    artifact.id(),
                    type,
                    artifact.title(),
                    artifact.imageLabel(),
                    artifact.imageUrl(),
                    artifact.description(),
                    isFavorite(userId, artifact.id())
                )
            ));
        }
        return records;
    }

    private List<ArtifactRecord> buildFavoriteRecords(Long userId, String lang) {
        List<ArtifactRecord> records = new ArrayList<>();
        for (UserFavoriteEntity favorite : userFavoriteRepository.findByUserIdOrderByCreatedAtDesc(userId)) {
            findArtifactPayload(favorite.getArtifactId(), lang).ifPresent(artifact -> records.add(
                new ArtifactRecord(
                    artifact.id(),
                    "favorites",
                    artifact.title(),
                    artifact.imageLabel(),
                    artifact.imageUrl(),
                    artifact.description(),
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

    private PuzzleArtifactEntity getOrAssignCurrentPuzzleArtifact(Long userId) {
        Long artifactId = currentPuzzleArtifactIdByUser.computeIfAbsent(userId, ignored -> chooseRandomPuzzleArtifact().getId());
        return puzzleArtifactRepository.findById(artifactId).orElseGet(this::chooseRandomPuzzleArtifact);
    }

    private ArtifactPayload assignRandomArtifactToOfflineScan(Long userId, String lang) {
        ArtifactPayload artifact = chooseRandomOfflineArtifact(lang);
        currentOfflineArtifactIdByUser.put(userId, artifact.id());
        return artifact;
    }

    private ArtifactPayload getOrAssignOfflineArtifact(Long userId) {
        Long artifactId = currentOfflineArtifactIdByUser.computeIfAbsent(userId, ignored -> chooseRandomOfflineArtifact("en").id());
        return findArtifactPayload(artifactId, "en").orElseGet(() -> chooseRandomOfflineArtifact("en"));
    }

    private ArtifactEntity chooseRandomArtifact() {
        List<ArtifactEntity> artifacts = artifactRepository.findAll();
        if (artifacts.isEmpty()) {
            throw new IllegalStateException("No artifacts are available.");
        }
        Collections.shuffle(artifacts);
        return artifacts.getFirst();
    }

    private PuzzleArtifactEntity chooseRandomPuzzleArtifact() {
        List<PuzzleArtifactEntity> artifacts = puzzleArtifactRepository.findAll();
        if (artifacts.isEmpty()) {
            throw new IllegalStateException("No puzzle artifacts are available.");
        }
        Collections.shuffle(artifacts);
        return artifacts.getFirst();
    }

    private ArtifactPayload chooseRandomOfflineArtifact(String lang) {
        List<ArtifactPayload> combined = new ArrayList<>();
        for (ArtifactEntity artifact : artifactRepository.findAll()) {
            combined.add(toPayload(artifact, lang));
        }
        for (PuzzleArtifactEntity artifact : puzzleArtifactRepository.findAll()) {
            combined.add(toPayload(artifact, lang));
        }
        if (combined.isEmpty()) {
            throw new IllegalStateException("No artifacts are available.");
        }
        Collections.shuffle(combined);
        return combined.getFirst();
    }

    private void saveRecordIfAbsent(Long userId, Long artifactId, String recordType) {
        if (userArtifactRecordRepository.findByUserIdAndArtifactIdAndRecordType(userId, artifactId, recordType).isEmpty()) {
            userArtifactRecordRepository.save(new UserArtifactRecordEntity(userId, artifactId, recordType, LocalDateTime.now()));
        }
    }

    private boolean matchesArtifactNameOrDescription(ArtifactEntity artifact, String normalizedAnswer, String lang) {
        if (containsValue(normalizedAnswer, artifact.getTitle())
            || containsValue(normalizedAnswer, artifact.getTitleZh())
            || containsValue(normalizedAnswer, artifact.getImageLabel())
            || containsValue(normalizedAnswer, artifact.getImageLabelZh())) {
            return true;
        }

        Set<String> keywords = new LinkedHashSet<>();
        keywords.addAll(extractKeywords(artifact.getDescriptionByLanguage(normalizeLang(lang))));
        keywords.addAll(extractKeywords(artifact.getBackgroundStoryByLanguage(normalizeLang(lang))));
        keywords.addAll(extractKeywords(artifact.getDescription()));
        keywords.addAll(extractKeywords(artifact.getDescriptionZh()));
        keywords.addAll(extractKeywords(artifact.getBackgroundStory()));
        keywords.addAll(extractKeywords(artifact.getBackgroundStoryZh()));

        for (String keyword : keywords) {
            if (normalizedAnswer.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsValue(String normalizedAnswer, String value) {
        return value != null && !value.isBlank() && normalizedAnswer.contains(value.trim().toLowerCase());
    }

    private Set<String> extractKeywords(String source) {
        Set<String> keywords = new HashSet<>();
        if (source == null || source.isBlank()) {
            return keywords;
        }

        String lowered = source.toLowerCase();
        Matcher englishMatcher = Pattern.compile("[a-z]{4,}").matcher(lowered);
        while (englishMatcher.find()) {
            String word = englishMatcher.group();
            if (!isCommonEnglishWord(word)) {
                keywords.add(word);
            }
        }

        Matcher chineseMatcher = Pattern.compile("[\\u4e00-\\u9fff]{2,}").matcher(source);
        while (chineseMatcher.find()) {
            String segment = chineseMatcher.group().trim().toLowerCase();
            if (segment.length() >= 2) {
                keywords.add(segment);
            }
        }
        return keywords;
    }

    private boolean isCommonEnglishWord(String word) {
        return Set.of(
            "this", "that", "with", "from", "were", "been", "have", "has", "into", "their",
            "there", "which", "while", "where", "about", "after", "before", "would", "could",
            "should", "these", "those", "than", "then", "they", "them", "used", "made",
            "make", "using", "also", "very", "more", "most", "such", "only", "some",
            "many", "much", "each", "other", "another", "through", "during", "because",
            "being", "inside", "outside", "around", "story", "artifact", "museum"
        ).contains(word);
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

    private ArtifactDetail toDetail(Long userId, PuzzleArtifactEntity artifact, String lang) {
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

    private ArtifactPayload toPayload(ArtifactEntity artifact, String lang) {
        return new ArtifactPayload(
            artifact.getId(),
            artifact.getTitleByLanguage(normalizeLang(lang)),
            artifact.getImageLabelByLanguage(normalizeLang(lang)),
            artifact.getImageUrl(),
            artifact.getThumbnailUrl(),
            artifact.getDescriptionByLanguage(normalizeLang(lang)),
            artifact.getCategory()
        );
    }

    private ArtifactPayload toPayload(PuzzleArtifactEntity artifact, String lang) {
        return new ArtifactPayload(
            artifact.getId(),
            artifact.getTitleByLanguage(normalizeLang(lang)),
            artifact.getImageLabelByLanguage(normalizeLang(lang)),
            artifact.getImageUrl(),
            artifact.getThumbnailUrl(),
            artifact.getDescriptionByLanguage(normalizeLang(lang)),
            artifact.getCategory()
        );
    }

    private java.util.Optional<ArtifactPayload> findArtifactPayload(Long artifactId, String lang) {
        return artifactRepository.findById(artifactId)
            .map(artifact -> toPayload(artifact, lang))
            .or(() -> puzzleArtifactRepository.findById(artifactId).map(artifact -> toPayload(artifact, lang)));
    }

    private boolean matchesCatalogQuery(ArtifactDetail detail, String keyword) {
        if (keyword.isBlank()) {
            return true;
        }
        return detail.getTitle().toLowerCase().contains(keyword)
            || detail.getDescription().toLowerCase().contains(keyword);
    }

    private String normalizeLang(String lang) {
        return "zh".equalsIgnoreCase(lang) ? "zh" : "en";
    }

    private record ArtifactPayload(
        Long id,
        String title,
        String imageLabel,
        String imageUrl,
        String thumbnailUrl,
        String description,
        String category
    ) {
    }

    public record TurtleScenario(String title, String helperText, ArtifactDetail artifact) {
    }

    public record TurtleAnswerResult(boolean correct, String message, ArtifactDetail artifact) {
    }

    public record FavoriteToggleResponseData(boolean active, String message) {
    }
}

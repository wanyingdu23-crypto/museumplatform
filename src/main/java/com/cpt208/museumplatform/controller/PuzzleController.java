package com.cpt208.museumplatform.controller;

import com.cpt208.museumplatform.dto.ApiResponse;
import com.cpt208.museumplatform.dto.FavoriteToggleResponse;
import com.cpt208.museumplatform.dto.PuzzleFinishResponse;
import com.cpt208.museumplatform.dto.PuzzleGameResponse;
import com.cpt208.museumplatform.dto.PuzzleHintResponse;
import com.cpt208.museumplatform.dto.PuzzleStateRequest;
import com.cpt208.museumplatform.model.ArtifactDetail;
import com.cpt208.museumplatform.model.User;
import com.cpt208.museumplatform.service.InteractionService;
import com.cpt208.museumplatform.service.UserSessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/puzzle")
public class PuzzleController extends BaseUserController {

    private final InteractionService interactionService;
    private final UserSessionService userSessionService;

    public PuzzleController(InteractionService interactionService, UserSessionService userSessionService) {
        this.interactionService = interactionService;
        this.userSessionService = userSessionService;
    }

    @GetMapping("/game")
    public PuzzleGameResponse getGame(@RequestParam(defaultValue = "en") String lang, HttpSession session) {
        User user = requireUser(session, userSessionService);
        return interactionService.getPuzzleGame(user.getId(), lang);
    }

    @PostMapping("/hint")
    public PuzzleHintResponse getHint(@RequestBody PuzzleStateRequest request, HttpSession session) {
        requireUser(session, userSessionService);
        return new PuzzleHintResponse(interactionService.choosePuzzleHint(request.placedPieceIds()));
    }

    @PostMapping("/finish")
    public PuzzleFinishResponse finish(@RequestParam(defaultValue = "en") String lang, @RequestBody PuzzleStateRequest request, HttpSession session) {
        User user = requireUser(session, userSessionService);
        return interactionService.finishPuzzle(user.getId(), request.placedPieceIds(), lang);
    }

    @GetMapping("/artifact")
    public ArtifactDetail getArtifact(@RequestParam(defaultValue = "en") String lang, HttpSession session) {
        User user = requireUser(session, userSessionService);
        return interactionService.getCurrentPuzzleArtifact(user.getId(), lang);
    }

    @PostMapping("/next")
    public ApiResponse nextGame(HttpSession session) {
        User user = requireUser(session, userSessionService);
        interactionService.moveToNextPuzzleArtifact(user.getId());
        return new ApiResponse(true, "Next puzzle ready.");
    }

    @PostMapping("/favorite/{artifactId}")
    public FavoriteToggleResponse toggleFavorite(@PathVariable Long artifactId, HttpSession session) {
        User user = requireUser(session, userSessionService);
        InteractionService.FavoriteToggleResponseData result = interactionService.toggleFavorite(user.getId(), artifactId);
        return new FavoriteToggleResponse(result.active(), result.message());
    }
}

package com.cpt208.museumplatform.controller;

import com.cpt208.museumplatform.dto.ApiResponse;
import com.cpt208.museumplatform.dto.FavoriteToggleResponse;
import com.cpt208.museumplatform.dto.TurtleAnswerRequest;
import com.cpt208.museumplatform.dto.TurtleAnswerResponse;
import com.cpt208.museumplatform.dto.TurtleRoundResponse;
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
@RequestMapping("/api/turtle-soup")
public class TurtleSoupController extends BaseUserController {

    private final InteractionService interactionService;
    private final UserSessionService userSessionService;

    public TurtleSoupController(InteractionService interactionService, UserSessionService userSessionService) {
        this.interactionService = interactionService;
        this.userSessionService = userSessionService;
    }

    @GetMapping("/round")
    public TurtleRoundResponse getRound(@RequestParam(defaultValue = "en") String lang, HttpSession session) {
        User user = requireUser(session, userSessionService);
        InteractionService.TurtleScenario scenario = interactionService.getCurrentTurtleScenario(user.getId(), lang);
        return new TurtleRoundResponse(scenario.title(), scenario.helperText());
    }

    @PostMapping("/answer")
    public TurtleAnswerResponse submitAnswer(@RequestParam(defaultValue = "en") String lang, @RequestBody TurtleAnswerRequest request, HttpSession session) {
        User user = requireUser(session, userSessionService);
        InteractionService.TurtleAnswerResult result = interactionService.submitTurtleAnswer(user.getId(), request.answer(), lang);
        return new TurtleAnswerResponse(result.correct(), result.message(), result.artifact());
    }

    @GetMapping("/artifact")
    public ArtifactDetail getArtifact(@RequestParam(defaultValue = "en") String lang, HttpSession session) {
        User user = requireUser(session, userSessionService);
        return interactionService.revealCurrentTurtleArtifact(user.getId(), lang);
    }

    @PostMapping("/next")
    public ApiResponse nextRound(HttpSession session) {
        User user = requireUser(session, userSessionService);
        interactionService.moveToNextTurtleScenario(user.getId());
        return new ApiResponse(true, "Next round ready.");
    }

    @PostMapping("/favorite/{artifactId}")
    public FavoriteToggleResponse toggleFavorite(@PathVariable Long artifactId, HttpSession session) {
        User user = requireUser(session, userSessionService);
        InteractionService.FavoriteToggleResponseData result = interactionService.toggleFavorite(user.getId(), artifactId);
        return new FavoriteToggleResponse(result.active(), result.message());
    }
}

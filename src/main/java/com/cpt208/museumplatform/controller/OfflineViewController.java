package com.cpt208.museumplatform.controller;

import com.cpt208.museumplatform.dto.FavoriteToggleResponse;
import com.cpt208.museumplatform.dto.OfflineAskResponse;
import com.cpt208.museumplatform.dto.TurtleAnswerRequest;
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

import java.util.List;

@RestController
@RequestMapping("/api/offline-view")
public class OfflineViewController extends BaseUserController {

    private final InteractionService interactionService;
    private final UserSessionService userSessionService;

    public OfflineViewController(InteractionService interactionService, UserSessionService userSessionService) {
        this.interactionService = interactionService;
        this.userSessionService = userSessionService;
    }

    @GetMapping("/scan")
    public List<ArtifactDetail> scan(@RequestParam(defaultValue = "en") String lang, HttpSession session) {
        User user = requireUser(session, userSessionService);
        return interactionService.getOfflineArtifacts(user.getId(), lang);
    }

    @PostMapping("/ask")
    public OfflineAskResponse ask(@RequestBody TurtleAnswerRequest request, HttpSession session) {
        User user = requireUser(session, userSessionService);
        return new OfflineAskResponse(interactionService.askOfflineAssistant(user.getId(), request.answer()));
    }

    @PostMapping("/favorite/{artifactId}")
    public FavoriteToggleResponse toggleFavorite(@PathVariable Long artifactId, HttpSession session) {
        User user = requireUser(session, userSessionService);
        InteractionService.FavoriteToggleResponseData result = interactionService.toggleFavorite(user.getId(), artifactId);
        return new FavoriteToggleResponse(result.active(), result.message());
    }
}

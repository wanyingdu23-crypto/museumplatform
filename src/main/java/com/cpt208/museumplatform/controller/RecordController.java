package com.cpt208.museumplatform.controller;

import com.cpt208.museumplatform.model.ArtifactRecord;
import com.cpt208.museumplatform.dto.FavoriteToggleResponse;
import com.cpt208.museumplatform.service.RecordService;
import com.cpt208.museumplatform.service.InteractionService;
import com.cpt208.museumplatform.service.UserSessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class RecordController extends BaseUserController {

    private final RecordService recordService;
    private final InteractionService interactionService;
    private final UserSessionService userSessionService;

    public RecordController(RecordService recordService, InteractionService interactionService, UserSessionService userSessionService) {
        this.recordService = recordService;
        this.interactionService = interactionService;
        this.userSessionService = userSessionService;
    }

    @GetMapping
    public List<ArtifactRecord> getRecords(@RequestParam(defaultValue = "favorites") String type, @RequestParam(defaultValue = "en") String lang, HttpSession session) {
        Long userId = requireUser(session, userSessionService).getId();
        return recordService.getRecords(userId, type, lang);
    }

    @PostMapping("/favorite/{artifactId}")
    public FavoriteToggleResponse toggleFavorite(@PathVariable Long artifactId, HttpSession session) {
        Long userId = requireUser(session, userSessionService).getId();
        InteractionService.FavoriteToggleResponseData result = interactionService.toggleFavorite(userId, artifactId);
        return new FavoriteToggleResponse(result.active(), result.message());
    }
}

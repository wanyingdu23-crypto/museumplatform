package com.cpt208.museumplatform.controller;

import com.cpt208.museumplatform.model.ArtifactDetail;
import com.cpt208.museumplatform.model.User;
import com.cpt208.museumplatform.service.InteractionService;
import com.cpt208.museumplatform.service.UserSessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController extends BaseUserController {

    private final InteractionService interactionService;
    private final UserSessionService userSessionService;

    public CatalogController(InteractionService interactionService, UserSessionService userSessionService) {
        this.interactionService = interactionService;
        this.userSessionService = userSessionService;
    }

    @GetMapping
    public List<ArtifactDetail> getCatalog(
        @RequestParam(defaultValue = "en") String lang,
        @RequestParam(defaultValue = "") String query,
        HttpSession session
    ) {
        User user = requireUser(session, userSessionService);
        return interactionService.getCatalogArtifacts(user.getId(), lang, query);
    }
}

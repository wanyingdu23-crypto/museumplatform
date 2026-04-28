package com.cpt208.museumplatform.controller;

import com.cpt208.museumplatform.model.User;
import com.cpt208.museumplatform.service.UserSessionService;
import jakarta.servlet.http.HttpSession;

public abstract class BaseUserController {

    protected static final String SESSION_USER_ID = "museumStoriesUserId";

    protected User requireUser(HttpSession session, UserSessionService userSessionService) {
        Object userId = session.getAttribute(SESSION_USER_ID);
        if (!(userId instanceof Long id)) {
            throw new UnauthorizedException("Please log in first.");
        }
        return userSessionService.findById(id)
            .orElseThrow(() -> new UnauthorizedException("Please log in first."));
    }
}

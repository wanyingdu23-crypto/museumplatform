package com.cpt208.museumplatform.controller;

import com.cpt208.museumplatform.dto.ApiResponse;
import com.cpt208.museumplatform.dto.LoginRequest;
import com.cpt208.museumplatform.dto.ProfileResponse;
import com.cpt208.museumplatform.dto.RegisterRequest;
import com.cpt208.museumplatform.model.User;
import com.cpt208.museumplatform.service.UserSessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseUserController {

    private final UserSessionService userSessionService;

    public AuthController(UserSessionService userSessionService) {
        this.userSessionService = userSessionService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse register(@RequestBody RegisterRequest request, HttpSession session) {
        User user = userSessionService.register(request);
        session.setAttribute(SESSION_USER_ID, user.getId());
        return toProfileResponse(user);
    }

    @PostMapping("/login")
    public ProfileResponse login(@RequestBody LoginRequest request, HttpSession session) {
        User user = userSessionService.login(request);
        session.setAttribute(SESSION_USER_ID, user.getId());
        return toProfileResponse(user);
    }

    @GetMapping("/me")
    public ProfileResponse currentUser(HttpSession session) {
        return toProfileResponse(requireUser(session, userSessionService));
    }

    @PostMapping("/logout")
    public ApiResponse logout(HttpSession session) {
        session.invalidate();
        return new ApiResponse(true, "Signed out successfully.");
    }

    @DeleteMapping("/account")
    public ApiResponse deleteAccount(HttpSession session) {
        User user = requireUser(session, userSessionService);
        userSessionService.deleteUser(user.getId());
        session.invalidate();
        return new ApiResponse(true, "Account deleted.");
    }

    private ProfileResponse toProfileResponse(User user) {
        return new ProfileResponse(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
    }
}

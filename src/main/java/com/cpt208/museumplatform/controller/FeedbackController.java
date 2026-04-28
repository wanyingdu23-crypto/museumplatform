package com.cpt208.museumplatform.controller;

import com.cpt208.museumplatform.dto.ApiResponse;
import com.cpt208.museumplatform.dto.FeedbackRequest;
import com.cpt208.museumplatform.model.User;
import com.cpt208.museumplatform.service.FeedbackService;
import com.cpt208.museumplatform.service.UserSessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController extends BaseUserController {

    private final FeedbackService feedbackService;
    private final UserSessionService userSessionService;

    public FeedbackController(FeedbackService feedbackService, UserSessionService userSessionService) {
        this.feedbackService = feedbackService;
        this.userSessionService = userSessionService;
    }

    @PostMapping
    public ApiResponse submitFeedback(@RequestBody FeedbackRequest request, HttpSession session) {
        User user = requireUser(session, userSessionService);
        feedbackService.submit(user, request.content());
        return new ApiResponse(true, "Thank you for your suggestion.");
    }
}

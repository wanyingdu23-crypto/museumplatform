package com.cpt208.museumplatform.service;

import com.cpt208.museumplatform.model.FeedbackSubmission;
import com.cpt208.museumplatform.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class FeedbackService {

    private final AtomicLong feedbackIdSequence = new AtomicLong(1);
    private final List<FeedbackSubmission> submissions = new CopyOnWriteArrayList<>();

    public FeedbackSubmission submit(User user, String content) {
        String message = content == null ? "" : content.trim();
        if (message.isBlank()) {
            throw new IllegalArgumentException("Please enter your feedback.");
        }

        FeedbackSubmission submission = new FeedbackSubmission(
            feedbackIdSequence.getAndIncrement(),
            user.getId(),
            user.getUsername(),
            message,
            LocalDateTime.now()
        );
        submissions.add(submission);
        return submission;
    }

    public List<FeedbackSubmission> getSubmissions() {
        return List.copyOf(submissions);
    }
}

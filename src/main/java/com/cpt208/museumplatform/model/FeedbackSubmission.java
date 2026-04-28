package com.cpt208.museumplatform.model;

import java.time.LocalDateTime;

public class FeedbackSubmission {

    private final Long id;
    private final Long userId;
    private final String username;
    private final String content;
    private final LocalDateTime createdAt;

    public FeedbackSubmission(Long id, Long userId, String username, String content, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

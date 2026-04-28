package com.cpt208.museumplatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "user_favorites",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "artifact_id"})
)
public class UserFavoriteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "artifact_id", nullable = false)
    private Long artifactId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected UserFavoriteEntity() {
    }

    public UserFavoriteEntity(Long userId, Long artifactId, LocalDateTime createdAt) {
        this.userId = userId;
        this.artifactId = artifactId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getArtifactId() {
        return artifactId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

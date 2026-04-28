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
    name = "user_artifact_records",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "artifact_id", "record_type"})
)
public class UserArtifactRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "artifact_id", nullable = false)
    private Long artifactId;

    @Column(name = "record_type", nullable = false, length = 30)
    private String recordType;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected UserArtifactRecordEntity() {
    }

    public UserArtifactRecordEntity(Long userId, Long artifactId, String recordType, LocalDateTime createdAt) {
        this.userId = userId;
        this.artifactId = artifactId;
        this.recordType = recordType;
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

    public String getRecordType() {
        return recordType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

package com.cpt208.museumplatform.repository;

import com.cpt208.museumplatform.entity.UserArtifactRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserArtifactRecordRepository extends JpaRepository<UserArtifactRecordEntity, Long> {

    Optional<UserArtifactRecordEntity> findByUserIdAndArtifactIdAndRecordType(Long userId, Long artifactId, String recordType);

    List<UserArtifactRecordEntity> findByUserIdAndRecordTypeOrderByCreatedAtDesc(Long userId, String recordType);
}

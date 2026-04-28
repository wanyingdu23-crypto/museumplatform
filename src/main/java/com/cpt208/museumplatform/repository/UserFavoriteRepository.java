package com.cpt208.museumplatform.repository;

import com.cpt208.museumplatform.entity.UserFavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserFavoriteRepository extends JpaRepository<UserFavoriteEntity, Long> {

    Optional<UserFavoriteEntity> findByUserIdAndArtifactId(Long userId, Long artifactId);

    boolean existsByUserIdAndArtifactId(Long userId, Long artifactId);

    List<UserFavoriteEntity> findByUserIdOrderByCreatedAtDesc(Long userId);

    void deleteByUserIdAndArtifactId(Long userId, Long artifactId);
}

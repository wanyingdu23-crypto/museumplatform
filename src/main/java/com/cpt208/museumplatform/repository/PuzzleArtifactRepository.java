package com.cpt208.museumplatform.repository;

import com.cpt208.museumplatform.entity.PuzzleArtifactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PuzzleArtifactRepository extends JpaRepository<PuzzleArtifactEntity, Long> {

    List<PuzzleArtifactEntity> findByCategoryIgnoreCase(String category);
}

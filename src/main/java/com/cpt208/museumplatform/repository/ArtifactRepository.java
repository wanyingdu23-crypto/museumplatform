package com.cpt208.museumplatform.repository;

import com.cpt208.museumplatform.entity.ArtifactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtifactRepository extends JpaRepository<ArtifactEntity, Long> {

    List<ArtifactEntity> findByCategoryIgnoreCase(String category);
}

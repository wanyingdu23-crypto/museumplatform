package com.cpt208.museumplatform.dto;

import com.cpt208.museumplatform.model.ArtifactDetail;

public record PuzzleFinishResponse(boolean complete, String message, ArtifactDetail artifact) {
}

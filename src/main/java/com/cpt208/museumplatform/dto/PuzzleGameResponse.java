package com.cpt208.museumplatform.dto;

import com.cpt208.museumplatform.model.ArtifactDetail;

import java.util.List;

public record PuzzleGameResponse(List<Integer> pieceOrder, ArtifactDetail artifact) {
}

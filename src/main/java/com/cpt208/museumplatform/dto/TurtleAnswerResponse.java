package com.cpt208.museumplatform.dto;

import com.cpt208.museumplatform.model.ArtifactDetail;

public record TurtleAnswerResponse(boolean correct, String message, ArtifactDetail artifact) {
}

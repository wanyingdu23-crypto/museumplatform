package com.cpt208.museumplatform.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    private final InteractionService interactionService;

    public RecordService(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    public List<com.cpt208.museumplatform.model.ArtifactRecord> getRecords(Long userId, String type, String lang) {
        return interactionService.getRecords(userId, type, lang);
    }
}

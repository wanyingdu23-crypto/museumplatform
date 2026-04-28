package com.cpt208.museumplatform.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class DeepSeekService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${deepseek.api.url:https://api.deepseek.com/chat/completions}")
    private String apiUrl;

    @Value("${deepseek.api.key:}")
    private String apiKey;

    @Value("${deepseek.api.model:deepseek-chat}")
    private String model;

    public String createTurtleQuestion(String artifactTitle, String backgroundStory) {
        String fallback = "A hidden artifact is behind this story. Ask questions and guess which artifact it is.";
        return chat(
            "You design short turtle soup style riddles for a museum app.",
            "Create one short turtle soup question in English based on this artifact.\nArtifact: " + artifactTitle +
                "\nBackground: " + backgroundStory +
                "\nRules: do not reveal the artifact name, keep it under 30 words, return only the question.",
            fallback
        );
    }

    public String replyForWrongTurtleGuess(String artifactTitle, String userAnswer) {
        String fallback = "Incorrect. Try another guess.";
        return chat(
            "You are a concise museum game assistant.",
            "The correct artifact is " + artifactTitle + ". The player's answer was: " + userAnswer +
                ". Reply in one short English sentence telling them the answer is incorrect without revealing the artifact.",
            fallback
        );
    }

    public String askArtifactAssistant(String artifactTitle, String description, String question) {
        String fallback = "This artifact may relate to ritual use, visual symbolism, and historical daily life.";
        return chat(
            "You are a museum explainer inside a mobile app.",
            "Artifact: " + artifactTitle + "\nDescription: " + description + "\nUser question: " + question +
                "\nReply in concise English in 2 to 4 sentences.",
            fallback
        );
    }

    private String chat(String systemPrompt, String userPrompt, String fallback) {
        if (apiKey == null || apiKey.isBlank()) {
            return fallback;
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> body = Map.of(
                "model", model,
                "messages", List.of(
                    Map.of("role", "system", "content", systemPrompt),
                    Map.of("role", "user", "content", userPrompt)
                ),
                "temperature", 0.8
            );

            String rawResponse = restTemplate.postForObject(apiUrl, new HttpEntity<>(body, headers), String.class);
            if (rawResponse == null || rawResponse.isBlank()) {
                return fallback;
            }

            JsonNode root = objectMapper.readTree(rawResponse);
            JsonNode contentNode = root.path("choices").path(0).path("message").path("content");
            if (contentNode.isMissingNode() || contentNode.asText().isBlank()) {
                return fallback;
            }
            return contentNode.asText().trim();
        } catch (Exception exception) {
            return fallback;
        }
    }
}

package com.gemini.ai.chat.gemini_ai_chat.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class GeminiService {

    @Value("${gemini.api.url}")
    private String gemiUrl;

    @Value("${gemini.api.key}")
    private String geminiKey;

    private final WebClient webClient;

    public GeminiService(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }


    public String getAnswer(String question) {
        Map<String, Object> requestBody = Map.of("contents", new Object[]{
                Map.of("parts", new Object[]{
                        Map.of("text", question)
                })
        });

        return webClient.post()
                .uri(gemiUrl+geminiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}

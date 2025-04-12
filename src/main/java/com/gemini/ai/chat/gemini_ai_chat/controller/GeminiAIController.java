package com.gemini.ai.chat.gemini_ai_chat.controller;

import com.gemini.ai.chat.gemini_ai_chat.service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1")
public class GeminiAIController {


    private final GeminiService geminiService;

    public GeminiAIController(GeminiService geminiService){
        this.geminiService = geminiService;
    }

    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestBody Map<String, String> payload){
        String question = payload.get("question");

        String answer = geminiService.getAnswer(question);

        return ResponseEntity.ok(answer);

    }
}

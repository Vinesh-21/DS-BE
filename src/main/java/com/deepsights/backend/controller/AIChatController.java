package com.deepsights.backend.controller;

import com.deepsights.backend.dto.ChatBotResponse;
import com.deepsights.backend.service.AIChatService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/api/v1/ai")
@CrossOrigin(origins = "*")
public class AIChatController {

    private final AIChatService aiChatService;

    public AIChatController(AIChatService aiChatService) {
        this.aiChatService = aiChatService;
    }

    @PostMapping("/chat")
    public ChatBotResponse chat(@RequestBody String prompt, @RequestParam String conversationId) {
        System.out.println(prompt);
        return aiChatService.chat(conversationId,prompt);

    }
}

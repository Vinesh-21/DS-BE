package com.deepsights.backend.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
public class AIChatService {

    public final ChatClient chatClient;
    public AIChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String conversationId, String userPrompt){

        return chatClient.prompt()
                .user(userPrompt)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
    }
}

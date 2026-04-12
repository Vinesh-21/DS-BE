package com.deepsights.backend.dto;

import com.deepsights.backend.enums.ContentType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"contentType", "content","jsonContent"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ChatBotResponse(
        ContentType contentType,
        String content,
         List<Object> jsonContent
) {}
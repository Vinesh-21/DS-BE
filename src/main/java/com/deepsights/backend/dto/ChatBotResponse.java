package com.deepsights.backend.dto;

import com.deepsights.backend.enums.ContentType;
import com.deepsights.backend.model.LoadReading;
import com.deepsights.backend.model.MeterReading;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"contentType", "content","jsonContent","jsonContentMeter","jsonContentLoad","steps","code"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ChatBotResponse(
        ContentType contentType,
        String content,
        List<MeterReading> jsonContentMeter,
        List<LoadReading> jsonContentLoad,
        List<GuideStep> steps,
        String code
) {}
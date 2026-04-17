package com.deepsights.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"text", "referenceImage"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public record GuideStep(
        String text,
        String referenceImage

) {
}

package com.deepsights.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "meter_readings")
public class MeterReading {

    @Id
    private String id;
    @Indexed
    private String meterId;

    private Double reading;

    private String unit;
    @Indexed
    private Long timestamp;

}
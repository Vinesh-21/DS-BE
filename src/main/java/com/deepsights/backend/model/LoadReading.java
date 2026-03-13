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
@Document(collection = "load_readings")
public class LoadReading {

    @Id
    private String id;
    @Indexed
    private String loadId;

    private Double power;

    private Double voltage;

    private Double current;
    @Indexed
    private Long timestamp;

}
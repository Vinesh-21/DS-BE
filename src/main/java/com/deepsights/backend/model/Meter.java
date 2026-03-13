package com.deepsights.backend.model;

import com.deepsights.backend.enums.MeterType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "meters")
public class Meter {


    @Id
    private String  id;
    @Indexed
    private String  gatewayId;
    private String meterName;
    private MeterType meterType;
}

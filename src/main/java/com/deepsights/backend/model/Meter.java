package com.deepsights.backend.model;

import com.deepsights.backend.enums.MeterType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "meters")
public class Meter {


    @Id
    private String  id;

    @Indexed(unique = true)
    private String meterId;
    private String meterName;
    private MeterType meterType;

    
    private String  gatewayId;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private  LocalDateTime updatedAt;
}

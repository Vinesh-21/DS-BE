package com.deepsights.backend.model;

import com.deepsights.backend.enums.LoadType;
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


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "loads")
public class Load {

    @Id
    private String  id;

    @Indexed(unique = true)
    private String loadId;
    private String loadName;
    private LoadType loadType;
    private String  gatewayId;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private  LocalDateTime updatedAt;
}

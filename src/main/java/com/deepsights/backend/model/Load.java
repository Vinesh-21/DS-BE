package com.deepsights.backend.model;

import com.deepsights.backend.enums.LoadType;
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
@Document(collection = "loads")
public class Load {

    @Id
    private String  id;
    @Indexed
    private String  gatewayId;
    private String loadName;
    private LoadType loadType;
}

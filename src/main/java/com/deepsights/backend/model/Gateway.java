package com.deepsights.backend.model;

import com.deepsights.backend.enums.GatewayStatus;
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
@Document(collection = "gateways")
public class Gateway {

    @Id
    private String  id;
    private String name;
    @Indexed
    private String  siteId;
    private GatewayStatus status;

}

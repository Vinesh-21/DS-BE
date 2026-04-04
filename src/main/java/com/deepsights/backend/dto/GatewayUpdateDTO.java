package com.deepsights.backend.dto;

import com.deepsights.backend.enums.GatewayStatus;
import lombok.Data;

@Data
public class GatewayUpdateDTO {
    private String gatewayName;
    private String siteId;
    private GatewayStatus status;
}

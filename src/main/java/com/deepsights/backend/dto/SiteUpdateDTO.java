package com.deepsights.backend.dto;

import com.deepsights.backend.enums.GatewayStatus;
import lombok.Data;

@Data
public class SiteUpdateDTO {
    private String siteName;

    private String siteLocation;

    private GatewayStatus active;
}

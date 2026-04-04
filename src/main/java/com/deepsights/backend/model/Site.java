package com.deepsights.backend.model;

import com.deepsights.backend.enums.GatewayStatus;
import jakarta.validation.constraints.NotEmpty;
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
@Document(collection = "sites")
public class Site {

    @Id
    private String  id;

    @NotEmpty(message = "SiteId is Required")
    @Indexed(unique = true)
    private String siteId;

    @NotEmpty(message = "Site Name is Required")
    private String siteName;

    @NotEmpty(message = "Site Location is Required")
    private String siteLocation;

    @Builder.Default
    private GatewayStatus active = GatewayStatus.ONLINE;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private  LocalDateTime updatedAt;
}

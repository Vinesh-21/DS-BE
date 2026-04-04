package com.deepsights.backend.service.query;

import com.deepsights.backend.model.Gateway;
import com.deepsights.backend.model.Load;
import com.deepsights.backend.model.Meter;
import com.deepsights.backend.model.Site;
import com.deepsights.backend.service.GatewayService;
import com.deepsights.backend.service.LoadService;
import com.deepsights.backend.service.MeterService;
import com.deepsights.backend.service.SiteService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
@Service
@RequiredArgsConstructor
public class SiteQueryService {

    private final SiteService siteService;
    private final GatewayService gatewayService;
    private final LoadService loadService;
    private final MeterService meterService;

    public Flux<Map<String, Object>> getFullSiteDetails() {

        return siteService.getAllSites()

                .flatMap(site ->

                        gatewayService.getGatewaysBySiteId(site.getSiteId())

                                .onErrorResume(e -> Flux.empty())

                                .flatMap(gateway ->

                                        Mono.zip(
                                                        loadService.getLoadsByGatewayId(gateway.getGatewayId())
                                                                .onErrorResume(e -> Flux.empty())
                                                                .collectList(),

                                                        meterService.getMetersByGatewayId(gateway.getGatewayId())
                                                                .onErrorResume(e -> Flux.empty())
                                                                .collectList()
                                                )
                                                .map(tuple -> {

                                                    Map<String, Object> gatewayMap = new LinkedHashMap<>();
                                                    gatewayMap.put("gatewayId", gateway.getGatewayId());
                                                    gatewayMap.put("gatewayName", gateway.getGatewayName());
                                                    gatewayMap.put("status", gateway.getStatus());
                                                    gatewayMap.put("loads", tuple.getT1());
                                                    gatewayMap.put("meters", tuple.getT2());

                                                    return gatewayMap;
                                                })
                                )

                                .collectList()

                                .map(gatewayList -> {

                                    Map<String, Object> siteMap = new LinkedHashMap<>();
                                    siteMap.put("siteId", site.getSiteId());
                                    siteMap.put("siteName", site.getSiteName());
                                    siteMap.put("location", site.getSiteLocation());
                                    siteMap.put("active", site.getActive());
                                    siteMap.put("gateways", gatewayList);

                                    return siteMap;
                                })
                );
    }
}
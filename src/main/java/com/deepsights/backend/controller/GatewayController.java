package com.deepsights.backend.controller;

import com.deepsights.backend.model.Gateway;
import com.deepsights.backend.service.GatewayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/gateways")
@RequiredArgsConstructor
public class GatewayController {

    private final GatewayService gatewayService;

    @GetMapping
    public Flux<Gateway> getAllGateways() {
        return gatewayService.getAllGateways();
    }

    @GetMapping("/{id}")
    public Mono<Gateway> getGatewayById(@PathVariable String id) {
        return gatewayService.getGatewayById(id);
    }

    @GetMapping("/site/{siteId}")
    public Flux<Gateway> getGatewaysBySiteId(@PathVariable String siteId) {
        return gatewayService.getGatewaysBySiteId(siteId);
    }

    @PostMapping
    public Mono<Gateway> createGateway(@RequestBody Gateway gateway) {
        return gatewayService.createGateway(gateway);
    }

    @PutMapping("/{id}")
    public Mono<Gateway> updateGateway(@PathVariable String id,
                                       @RequestBody Gateway gateway) {
        return gatewayService.updateGateway(id, gateway);
    }

    @DeleteMapping("/{id}")
    public Mono<Map<String,String>> deleteGateway(@PathVariable String id) {

        return gatewayService.deleteGateway(id)
                .map(message -> Map.of("message", message));
    }
}

package com.deepsights.backend.controller;

import com.deepsights.backend.dto.GatewayUpdateDTO;
import com.deepsights.backend.model.Gateway;
import com.deepsights.backend.service.GatewayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/gateways")
@RequiredArgsConstructor
public class GatewayController {

    private final GatewayService gatewayService;

    @GetMapping
    public Flux<Gateway> getAllGateways() {
        return gatewayService.getAllGateways();
    }

    @GetMapping("/{gatewayId}")
    public Mono<Gateway> getGatewayById(@PathVariable String gatewayId) {
        return gatewayService.getGatewayById(gatewayId);
    }

    @GetMapping("/site/{siteId}")
    public Flux<Gateway> getGatewaysBySiteId(@PathVariable String siteId) {
        return gatewayService.getGatewaysBySiteId(siteId);
    }

    @PostMapping
    public Mono<Gateway> createGateway(@RequestBody Gateway gateway) {
        return gatewayService.createGateway(gateway);
    }

    @PatchMapping("/{gatewayId}")
    public Mono<Gateway> updateGateway(@PathVariable String gatewayId,
                                       @RequestBody GatewayUpdateDTO gateway) {
        return gatewayService.updateGateway(gatewayId, gateway);
    }

    @DeleteMapping("/{gatewayId}")
    public Mono<Map<String,String>> deleteGateway(@PathVariable String gatewayId) {
        return gatewayService.deleteGateway(gatewayId)
                .map(message -> Map.of("message", message));
    }
}

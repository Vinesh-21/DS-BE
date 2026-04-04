package com.deepsights.backend.controller;


import com.deepsights.backend.model.Load;
import com.deepsights.backend.service.LoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/loads")
@RequiredArgsConstructor
public class LoadController {

    private final LoadService loadService;

    @GetMapping("/gateway/{gatewayId}")
    public Flux<Load> getLoadsByGateway(@PathVariable String gatewayId) {
        return loadService.getLoadsByGatewayId(gatewayId);
    }

    @PostMapping
    public Mono<Load> createLoad(@RequestBody Load load) {
        return loadService.createLoad(load);
    }

    @PutMapping("/{loadId}")
    public Mono<Load> updateLoad(@PathVariable String loadId,
                                 @RequestBody Load load) {
        return loadService.updateLoad(loadId, load);
    }

    @DeleteMapping("/{loadId}")
    public Mono<Map<String,String>> deleteLoad(@PathVariable String loadId) {

        return loadService.deleteLoad(loadId)
                .map(message -> Map.of("message", message));
    }
}
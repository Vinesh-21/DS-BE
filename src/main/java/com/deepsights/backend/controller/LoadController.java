package com.deepsights.backend.controller;


import com.deepsights.backend.model.Load;
import com.deepsights.backend.service.LoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

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

    @PutMapping("/{id}")
    public Mono<Load> updateLoad(@PathVariable String id,
                                 @RequestBody Load load) {
        return loadService.updateLoad(id, load);
    }

    @DeleteMapping("/{id}")
    public Mono<Map<String,String>> deleteLoad(@PathVariable String id) {

        return loadService.deleteLoad(id)
                .map(message -> Map.of("message", message));
    }
}
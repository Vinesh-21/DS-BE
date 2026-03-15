package com.deepsights.backend.controller;

import com.deepsights.backend.model.Meter;
import com.deepsights.backend.service.MeterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/meters")
@RequiredArgsConstructor
public class MeterController {

    private final MeterService meterService;

    @GetMapping("/gateway/{gatewayId}")
    public Flux<Meter> getMetersByGateway(@PathVariable String gatewayId) {
        return meterService.getMetersByGatewayId(gatewayId);
    }

    @PostMapping
    public Mono<Meter> createMeter(@RequestBody Meter meter) {
        return meterService.createMeter(meter);
    }

    @PutMapping("/{id}")
    public Mono<Meter> updateMeter(@PathVariable String id,
                                   @RequestBody Meter meter) {
        return meterService.updateMeter(id, meter);
    }

    @DeleteMapping("/{id}")
    public Mono<Map<String,String>> deleteMeter(@PathVariable String id) {

        return meterService.deleteMeter(id)
                .map(msg -> Map.of("message", msg));
    }
}
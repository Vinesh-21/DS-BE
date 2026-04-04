package com.deepsights.backend.controller;

import com.deepsights.backend.model.Meter;
import com.deepsights.backend.service.MeterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
@CrossOrigin(origins = "http://localhost:5173")
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

    @PutMapping("/{meterId}")
    public Mono<Meter> updateMeter(@PathVariable String meterId,
                                   @RequestBody Meter meter) {
        return meterService.updateMeter(meterId, meter);
    }

    @DeleteMapping("/{meterId}")
    public Mono<Map<String,String>> deleteMeter(@PathVariable String meterId) {

        return meterService.deleteMeter(meterId)
                .map(msg -> Map.of("message", msg));
    }
}
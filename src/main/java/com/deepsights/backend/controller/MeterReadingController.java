package com.deepsights.backend.controller;

import com.deepsights.backend.model.MeterReading;
import com.deepsights.backend.service.MeterReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/meter-readings")
@RequiredArgsConstructor
public class MeterReadingController {

    private final MeterReadingService meterReadingService;

    @GetMapping("/meter/{meterId}")
    public Flux<MeterReading> getReadingsByMeter(@PathVariable String meterId) {
        return meterReadingService.getReadingsByMeterId(meterId);
    }

    @PostMapping
    public Mono<MeterReading> createReading(@RequestBody MeterReading reading) {
        return meterReadingService.createReading(reading);
    }

    @DeleteMapping("/{id}")
    public Mono<Map<String,String>> deleteReading(@PathVariable String id) {

        return meterReadingService.deleteReading(id)
                .map(msg -> Map.of("message", msg));
    }
}
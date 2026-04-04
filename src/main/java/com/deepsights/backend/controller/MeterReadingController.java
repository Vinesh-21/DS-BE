package com.deepsights.backend.controller;

import com.deepsights.backend.model.MeterReading;
import com.deepsights.backend.service.MeterReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/meter-readings")
@RequiredArgsConstructor
public class MeterReadingController {

    private final MeterReadingService meterReadingService;

    @GetMapping("/meter/{meterId}")
    public Flux<MeterReading> getReadingsByMeter(@PathVariable String meterId) {
        long start = System.currentTimeMillis();

        return meterReadingService.getReadingsByMeterId(meterId).doOnComplete(() -> {
            long end = System.currentTimeMillis();
            System.out.println(meterId);
            System.out.println("Meter Time taken: " + (end - start) + " ms");
        });
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
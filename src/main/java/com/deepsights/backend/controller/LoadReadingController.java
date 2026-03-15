package com.deepsights.backend.controller;

import com.deepsights.backend.model.LoadReading;
import com.deepsights.backend.service.LoadReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/load-readings")
@RequiredArgsConstructor
public class LoadReadingController {

    private final LoadReadingService loadReadingService;

    @GetMapping("/load/{loadId}")
    public Flux<LoadReading> getReadingsByLoad(@PathVariable String loadId) {
        return loadReadingService.getReadingsByLoadId(loadId);
    }

    @PostMapping
    public Mono<LoadReading> createReading(@RequestBody LoadReading reading) {
        return loadReadingService.createReading(reading);
    }

    @DeleteMapping("/{id}")
    public Mono<Map<String,String>> deleteReading(@PathVariable String id) {

        return loadReadingService.deleteReading(id)
                .map(message -> Map.of("message", message));
    }
}
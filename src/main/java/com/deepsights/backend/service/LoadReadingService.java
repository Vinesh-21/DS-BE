package com.deepsights.backend.service;

import com.deepsights.backend.model.LoadReading;
import com.deepsights.backend.repository.LoadReadingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LoadReadingService {

    private final LoadReadingRepository loadReadingRepository;

    public Flux<LoadReading> getReadingsByLoadId(String loadId) {
        return loadReadingRepository.findByLoadId(loadId)
                .switchIfEmpty(Flux.error(new RuntimeException("Load Readings Not Found")));
    }

    public Mono<LoadReading> createReading(LoadReading reading) {
        return loadReadingRepository.save(reading);
    }

    public Mono<String> deleteReading(String id) {

        return loadReadingRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Load Reading Not Found")))
                .flatMap(loadReadingRepository::delete)
                .thenReturn("Load Reading Deleted Successfully");
    }
}
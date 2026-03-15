package com.deepsights.backend.service;

import com.deepsights.backend.model.MeterReading;
import com.deepsights.backend.repository.MeterReadingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MeterReadingService {

    private final MeterReadingRepository meterReadingRepository;

    public Flux<MeterReading> getReadingsByMeterId(String meterId) {
        return meterReadingRepository.findByMeterId(meterId)
                .switchIfEmpty(Flux.error(new RuntimeException("Meter Reading not Found")));
    }

    public Mono<MeterReading> createReading(MeterReading reading) {
        return meterReadingRepository.save(reading);
    }

    public Mono<String> deleteReading(String id) {

        return meterReadingRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Meter Reading Not Found")))
                .flatMap(meterReadingRepository::delete)
                .thenReturn("Meter Reading Deleted Successfully");
    }
}
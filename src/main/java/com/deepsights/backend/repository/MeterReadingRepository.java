package com.deepsights.backend.repository;

import com.deepsights.backend.model.MeterReading;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MeterReadingRepository extends ReactiveMongoRepository<MeterReading, String> {

    Flux<MeterReading> findByMeterId(String meterId);

}
package com.deepsights.backend.repository;

import com.deepsights.backend.model.LoadReading;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface LoadReadingRepository extends ReactiveMongoRepository<LoadReading, String> {

    Flux<LoadReading> findByLoadId(String loadId);

}

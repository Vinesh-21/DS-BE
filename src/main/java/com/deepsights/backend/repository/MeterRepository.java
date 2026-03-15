package com.deepsights.backend.repository;

import com.deepsights.backend.model.Meter;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MeterRepository extends ReactiveMongoRepository<Meter, String> {

    Flux<Meter> findByGatewayId(String gatewayId);

}
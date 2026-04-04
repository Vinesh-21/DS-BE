package com.deepsights.backend.repository;

import com.deepsights.backend.model.Gateway;
import com.deepsights.backend.model.Load;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LoadRepository extends ReactiveMongoRepository<Load,String> {
    Flux<Load> findByGatewayId(String gatewayId);
    Mono<Load> findByLoadId(String loadId);
    Mono<Boolean> existsByLoadId(String loadId);
}

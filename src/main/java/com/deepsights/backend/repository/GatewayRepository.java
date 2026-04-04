package com.deepsights.backend.repository;

import com.deepsights.backend.model.Gateway;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GatewayRepository extends ReactiveMongoRepository<Gateway,String> {


    Mono<Gateway> findByGatewayId(String gatewayId);
    
    Flux<Gateway> findBySiteId(String siteId);

    Mono<Void> deleteBySiteId(String siteId);
}

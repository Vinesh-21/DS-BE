package com.deepsights.backend.repository;

import com.deepsights.backend.model.Gateway;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface GatewayRepository extends ReactiveMongoRepository<Gateway,String> {

    Flux<Gateway> findBySiteId(String siteId);

}

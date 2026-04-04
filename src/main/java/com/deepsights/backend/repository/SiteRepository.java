package com.deepsights.backend.repository;

import com.deepsights.backend.model.Site;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Limit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface SiteRepository extends ReactiveMongoRepository<Site, String > {

    Mono<Site> findBySiteId(String siteId);

    Mono<Boolean> existsBySiteId(String siteId);

    Mono<Void> deleteBySiteId(String siteId);

}

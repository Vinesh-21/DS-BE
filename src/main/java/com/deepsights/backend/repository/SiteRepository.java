package com.deepsights.backend.repository;

import com.deepsights.backend.model.Site;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SiteRepository extends ReactiveMongoRepository<Site, String > {
}

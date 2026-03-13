package com.deepsights.backend.service;

import com.deepsights.backend.model.Site;
import com.deepsights.backend.repository.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SiteService {

    private final SiteRepository siteRepository;

    public Flux<Site> getAllSites() {
        return siteRepository.findAll();
    }

    public Mono<Site> getSiteById(String id) {
        return siteRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Site not found")));
    }

    public Mono<Site> createSite(Site site) {
        return siteRepository.save(site);
    }

    public Mono<Site> updateSite(String id, Site updatedSite) {

        return siteRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Site not found")))
                .flatMap(existingSite -> {

                    existingSite.setSiteName(updatedSite.getSiteName());
                    existingSite.setSiteLocation(updatedSite.getSiteLocation());
                    existingSite.setActive(updatedSite.getActive());

                    return siteRepository.save(existingSite);
                });
    }

    public Mono<String> deleteSite(String id) {

        return siteRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Site not found")))
                .flatMap(site -> siteRepository.deleteById(id))
                .thenReturn("Site Deleted Successfully");
    }
}
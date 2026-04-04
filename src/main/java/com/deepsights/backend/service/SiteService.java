package com.deepsights.backend.service;

import com.deepsights.backend.dto.SiteUpdateDTO;
import com.deepsights.backend.exception.DuplicateException;
import com.deepsights.backend.exception.NotFoundException;
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

    public Mono<Site> getSiteBySiteId(String id) {
        return siteRepository.findBySiteId(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Site not found")));
    }

    public Mono<Site> createSite(Site site) {

        return siteRepository.existsBySiteId(site.getSiteId()).flatMap((exists)->{
            if(exists){
                return Mono.error(new DuplicateException("Duplicate SiteId"));
            }

            return siteRepository.save(site);
        });
    }

    public Mono<Site> updateSite(String id, SiteUpdateDTO updatedSiteDTO) {

        return siteRepository.findBySiteId(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Site not found")))
                .flatMap(existingSite -> {

                    if (updatedSiteDTO.getSiteName() != null) {
                        existingSite.setSiteName(updatedSiteDTO.getSiteName());
                    }

                    if (updatedSiteDTO.getSiteLocation() != null) {
                        existingSite.setSiteLocation(updatedSiteDTO.getSiteLocation());
                    }

                    if (updatedSiteDTO.getActive() != null) {
                        existingSite.setActive(updatedSiteDTO.getActive());
                    }

                    return siteRepository.save(existingSite);
                });
    }

    public Mono<String> deleteSite(String id) {

        return siteRepository.findBySiteId(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Site not found")))
                .flatMap(site -> siteRepository.deleteBySiteId(id))
                .thenReturn("Site Deleted Successfully");
    }
}
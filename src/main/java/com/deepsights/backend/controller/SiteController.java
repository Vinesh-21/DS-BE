package com.deepsights.backend.controller;

import com.deepsights.backend.dto.SiteUpdateDTO;
import com.deepsights.backend.model.Site;
import com.deepsights.backend.repository.SiteRepository;
import com.deepsights.backend.service.SiteService;
import com.deepsights.backend.service.query.SiteQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/sites")
@RequiredArgsConstructor
public class SiteController {

    private final SiteService siteService;
    private final SiteQueryService siteQueryService;

    @GetMapping
    public Flux<Site> getAllSites() {
        return siteService.getAllSites();
    }

    @GetMapping("/{id}")
    public Mono<Site> getSiteById(@PathVariable String id) {
        return siteService.getSiteBySiteId(id);
    }

    @PostMapping
    public Mono<Site> createSite(@Valid @RequestBody Site site) {
        return siteService.createSite(site);
    }

    @PatchMapping("/{id}")
    public Mono<Site> updateSite(@PathVariable String id,
                                 @Valid @RequestBody SiteUpdateDTO site) {
        return siteService.updateSite(id, site);
    }

    @DeleteMapping("/{id}")
    public Mono<Map<String,String>> deleteSite(@PathVariable String id) {
        return siteService.deleteSite(id).map(message -> Map.of("message", message));
    }

    @GetMapping("/DetailedSiteView")
    public Flux<Map<String, Object>> getDetailedSiteView() {
        return siteQueryService.getFullSiteDetails();
    }
}

package com.deepsights.backend.controller;

import com.deepsights.backend.model.Site;
import com.deepsights.backend.repository.SiteRepository;
import com.deepsights.backend.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/sites")
@RequiredArgsConstructor
public class SiteController {

    private final SiteService siteService;

    @GetMapping
    public Flux<Site> getAllSites() {
        return siteService.getAllSites();
    }

    @GetMapping("/{id}")
    public Mono<Site> getSiteById(@PathVariable String id) {
        return siteService.getSiteById(id);
    }

    @PostMapping
    public Mono<Site> createSite(@RequestBody Site site) {
        return siteService.createSite(site);
    }

    @PutMapping("/{id}")
    public Mono<Site> updateSite(@PathVariable String id,
                                 @RequestBody Site site) {
        return siteService.updateSite(id, site);
    }

    @DeleteMapping("/{id}")
    public Mono<Map<String,String>> deleteSite(@PathVariable String id) {
        return siteService.deleteSite(id).map(message -> Map.of("message", message));
    }
}

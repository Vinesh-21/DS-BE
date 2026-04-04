package com.deepsights.backend.tools;

import com.deepsights.backend.model.Site;
import com.deepsights.backend.service.SiteService;
import com.deepsights.backend.service.query.SiteQueryService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@Component
public class SiteTool {


    private final SiteService siteService;
    private final SiteQueryService siteQueryService;

    public SiteTool(SiteService siteService,SiteQueryService siteQueryService) {
        this.siteService = siteService;
        this.siteQueryService = siteQueryService;
    }

    @Tool(name = "get_all_sites", description = "Fetch all sites from database including siteId, name and location")
    public List<Site> getAllSites(){
        return siteService.getAllSites().collectList().toFuture().join();
    }

    @Tool(
            name = "get_full_sites_details",
            description = """
    Returns full site hierarchy data including all gateways, loads,
    and meters nested under each site.

    Use this tool ONLY after a site has been confirmed to exist.
    Do NOT use this to check whether a site exists — use get_all_sites first.

    Use this tool to:
    - Get all loads under a confirmed site (for load visualization flow)
    - Get all meters under a confirmed site (for meter visualization flow)
    - Get site hierarchy for site visualization output
    - Answer data questions about gateways, loads, meters

    This tool returns RAW DATA — it is not a visualization.
    After calling this tool in a [LOAD] flow, list the available
    loads and ask the user which loadId to visualize.
    After calling this tool in a [METER] flow, list the available
    meters and ask the user which meterId to visualize.
    """
    )
    public List<Map<String, Object>>  getFullSiteDetails(){

        return siteQueryService.getFullSiteDetails().collectList().toFuture().join();
    }

}

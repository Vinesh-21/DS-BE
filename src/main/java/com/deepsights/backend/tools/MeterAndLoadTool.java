package com.deepsights.backend.tools;

import com.deepsights.backend.dto.ChatBotResponse;
import com.deepsights.backend.enums.ContentType;
import com.deepsights.backend.exception.NotFoundException;
import com.deepsights.backend.model.Gateway;
import com.deepsights.backend.model.LoadReading;
import com.deepsights.backend.model.MeterReading;
import com.deepsights.backend.service.GatewayService;
import com.deepsights.backend.service.LoadReadingService;
import com.deepsights.backend.service.MeterReadingService;
import com.deepsights.backend.service.query.SiteQueryService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MeterAndLoadTool {

    private final LoadReadingService loadReadingService;
    private final MeterReadingService meterReadingService;
    public MeterAndLoadTool(SiteQueryService siteQueryService, LoadReadingService loadReadingService, GatewayService gatewayService, MeterReadingService meterReadingService) {
        this.loadReadingService = loadReadingService;
        this.meterReadingService = meterReadingService;
    }

    @Tool(name = "get_Load_Readings_With_LoadId",description = """
Fetch load readings using loadId.

Guidelines:
- loadId is the only required parameter.
- If the user does not provide a loadId, ask for it.
- If the user is unsure about the loadId, ask for site or gateway to help identify it.
- Do NOT guess or fabricate loadId.
- Only call this tool when a valid loadId is clearly provided.

- If needed, you may use the get_full_sites_details tool to help identify or validate the correct loadId before calling this tool.

- If no matching loadId can be determined, respond that the load was not found.
""",returnDirect = true)
    public ChatBotResponse getLoadReadingsByLoadId(String loadId){
        List<LoadReading> loadReadings = loadReadingService.getReadingsByLoadId(loadId).collectList().toFuture().join();
        return new ChatBotResponse(ContentType.JSONFORLOAD,null,null,loadReadings,null);
    }


    @Tool(
            name = "get_Meter_Readings_With_MeterId",
            description = """
Fetch meter readings using meterId.

Guidelines:
- meterId is the only required parameter.
- If the user does not provide a meterId, ask for it.
- If the user is unsure about the meterId, ask for site or gateway to help identify it.
- Do NOT guess or fabricate meterId.
- Only call this tool when a valid meterId is clearly provided.

- If needed, you may use the get_full_sites_details tool to help identify or validate the correct meterId before calling this tool.

- If no matching meterId can be determined, respond that the meter was not found.
""",
            returnDirect = true
    )
    public ChatBotResponse getMeterReadingsByMeterId(String meterId){
        List<MeterReading> meterReadings= meterReadingService
                .getReadingsByMeterId(meterId)
                .collectList()
                .toFuture()
                .join();

        return new ChatBotResponse(ContentType.JSONFORMETER,null,meterReadings,null,null);
    }

}

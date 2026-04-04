package com.deepsights.backend.service;

import com.deepsights.backend.dto.GatewayUpdateDTO;
import com.deepsights.backend.exception.NotFoundException;
import com.deepsights.backend.model.Gateway;
import com.deepsights.backend.repository.GatewayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GatewayService {

    private final GatewayRepository gatewayRepository;

    public Flux<Gateway> getAllGateways(){
        return gatewayRepository.findAll();
    }

    public Mono<Gateway> getGatewayById(String gatewayId){
        return gatewayRepository.findByGatewayId(gatewayId)
                .switchIfEmpty(Mono.error(new NotFoundException("Gateway Not Found")));
    }

    public Flux<Gateway> getGatewaysBySiteId(String siteId) {
        return gatewayRepository.findBySiteId(siteId).switchIfEmpty(Flux.error(new NotFoundException("No Gateways related to siteID - "+siteId)));
    }

    public Mono<Gateway> createGateway(Gateway gateway){
        return gatewayRepository.save(gateway);
    }

    public Mono<Gateway> updateGateway(String id, GatewayUpdateDTO gatewayDTO){

        return gatewayRepository.findByGatewayId(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Gateway Not Found")))
                .flatMap(existingGateway -> {
                    if (gatewayDTO.getGatewayName() != null && !gatewayDTO.getGatewayName().isBlank()) {
                        existingGateway.setGatewayName(gatewayDTO.getGatewayName());
                    }

                    if (gatewayDTO.getSiteId() != null && !gatewayDTO.getSiteId().isBlank()) {
                        existingGateway.setSiteId(gatewayDTO.getSiteId());
                    }

                    if (gatewayDTO.getStatus() != null) {
                        existingGateway.setStatus(gatewayDTO.getStatus());
                    }

                    return gatewayRepository.save(existingGateway);
                });

    }

    public Mono<String> deleteGateway(String id) {

        return gatewayRepository.findByGatewayId(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Gateway Not Found")))
                .flatMap(gateway -> gatewayRepository.deleteById(gateway.getId()))
                .thenReturn("Gateway Deleted Successfully");
    }
}

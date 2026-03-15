package com.deepsights.backend.service;

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

    public Mono<Gateway> getGatewayById(String id){
        return gatewayRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Gateway Not Found")));
    }

    public Flux<Gateway> getGatewaysBySiteId(String siteId) {
        return gatewayRepository.findBySiteId(siteId);
    }

    public Mono<Gateway> createGateway(Gateway gateway){
        return gatewayRepository.save(gateway);
    }

    public Mono<Gateway> updateGateway(String id,Gateway gateway){

        return gatewayRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Gateway Not Found")))
                .flatMap(existingGateway -> {
                    existingGateway.setGatewayName(gateway.getGatewayName());
                    existingGateway.setSiteId(gateway.getSiteId());
                    existingGateway.setStatus(gateway.getStatus());

                    return gatewayRepository.save(existingGateway);
                });

    }

    public Mono<String> deleteGateway(String id) {

        return gatewayRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Gateway Not Found")))
                .flatMap(gateway -> gatewayRepository.deleteById(id))
                .thenReturn("Gateway Deleted Successfully");
    }
}

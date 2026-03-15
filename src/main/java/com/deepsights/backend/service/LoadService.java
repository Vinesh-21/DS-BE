package com.deepsights.backend.service;


import com.deepsights.backend.model.Load;
import com.deepsights.backend.repository.LoadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class LoadService {

    private final LoadRepository loadRepository;

    public Flux<Load> getLoadsByGatewayId(String gatewayId) {
        return loadRepository.findByGatewayId(gatewayId)
                .switchIfEmpty(Flux.error(new RuntimeException("Load Not Found")));
    }

    public Mono<Load> createLoad(Load load) {
        return loadRepository.save(load);
    }

    public Mono<Load> updateLoad(String id, Load load) {

        return loadRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Load not found")))
                .flatMap(existing -> {

                    existing.setGatewayId(load.getGatewayId());
                    existing.setLoadName(load.getLoadName());
                    existing.setLoadType(load.getLoadType());

                    return loadRepository.save(existing);
                });
    }

    public Mono<String> deleteLoad(String id) {

        return loadRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Load not found")))
                .flatMap(loadRepository::delete)
                .thenReturn("Load Deleted Successfully");
    }
}
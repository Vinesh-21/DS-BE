package com.deepsights.backend.service;


import com.deepsights.backend.exception.DuplicateException;
import com.deepsights.backend.exception.NotFoundException;
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
    private final GatewayService gatewayService;
    public Flux<Load> getLoadsByGatewayId(String gatewayId) {
        return loadRepository.findByGatewayId(gatewayId)
                .switchIfEmpty(Flux.error(new NotFoundException("GatewayId Not Found")));
    }

    public Mono<Load> createLoad(Load load) {

        return loadRepository.existsByLoadId(load.getLoadId())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new DuplicateException("LoadId already exists"));
                    }
                    return loadRepository.save(load);
                });
    }
    public Mono<Load> updateLoad(String loadId, Load load) {

        return loadRepository.findByLoadId(loadId)
                .switchIfEmpty(Mono.error(new NotFoundException("Load not found")))
                .flatMap(existing -> {
                    existing.setGatewayId(load.getGatewayId());
                    existing.setLoadName(load.getLoadName());
                    existing.setLoadType(load.getLoadType());
                    return loadRepository.save(existing);
                });
    }

    public Mono<String> deleteLoad(String loadId) {

        return loadRepository.findByLoadId(loadId)
                .switchIfEmpty(Mono.error(new NotFoundException("Load not found")))
                .flatMap(loadRepository::delete)
                .thenReturn("Load Deleted Successfully");
    }

}
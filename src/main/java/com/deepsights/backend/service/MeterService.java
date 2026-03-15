package com.deepsights.backend.service;

import com.deepsights.backend.model.Meter;
import com.deepsights.backend.repository.MeterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MeterService {

    private final MeterRepository meterRepository;

    public Flux<Meter> getMetersByGatewayId(String gatewayId) {
        return meterRepository.findByGatewayId(gatewayId)
                .switchIfEmpty(Flux.error(new RuntimeException("Meter not Found")));
    }

    public Mono<Meter> createMeter(Meter meter) {
        return meterRepository.save(meter);
    }

    public Mono<Meter> updateMeter(String id, Meter meter) {

        return meterRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Meter Not Found")))
                .flatMap(existing -> {

                    existing.setMeterName(meter.getMeterName());
                    existing.setMeterType(meter.getMeterType());

                    return meterRepository.save(existing);
                });
    }

    public Mono<String> deleteMeter(String id) {

        return meterRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Meter Not Found")))
                .flatMap(meterRepository::delete)
                .thenReturn("Meter Deleted Successfully");
    }
}
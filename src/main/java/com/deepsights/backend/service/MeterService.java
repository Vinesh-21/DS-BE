package com.deepsights.backend.service;

import com.deepsights.backend.exception.DuplicateException;
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

        return meterRepository.existsByMeterId(meter.getMeterId())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new DuplicateException("MeterId already exists"));
                    }
                    return meterRepository.save(meter);
                });
    }


    public Mono<Meter> updateMeter(String meterId, Meter meter) {

        return meterRepository.findByMeterId(meterId)
                .switchIfEmpty(Mono.error(new RuntimeException("Meter not found")))
                .flatMap(existing -> {

                    existing.setMeterName(meter.getMeterName());
                    existing.setMeterType(meter.getMeterType());
                    existing.setGatewayId(meter.getGatewayId());

                    return meterRepository.save(existing);
                });
    }

    public Mono<String> deleteMeter(String meterId) {

        return meterRepository.findByMeterId(meterId)
                .switchIfEmpty(Mono.error(new RuntimeException("Meter not found")))
                .flatMap(meterRepository::delete)
                .thenReturn("Meter Deleted Successfully");
    }
}
package com.lion.demo.misc;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetricsService {

    private final MeterRegistry meterRegistry;

    public void recordCustomMetrics() {
        // Counter example
        meterRegistry.counter("custom.metric.counter", "tag", "example").increment();

        // Timer example
        meterRegistry.timer("custom.metric.timer", "tag", "example")
                .record(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

}

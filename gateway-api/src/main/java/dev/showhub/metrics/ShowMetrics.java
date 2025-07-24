package dev.showhub.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShowMetrics {

    private final Counter createdCounter;

    public ShowMetrics(MeterRegistry registry) {
        createdCounter = Counter.builder("shows_created_total")
                .description("Total de shows criados via API")
                .register(registry);
    }

    public void incrementCreated() {
        createdCounter.increment();
    }
}

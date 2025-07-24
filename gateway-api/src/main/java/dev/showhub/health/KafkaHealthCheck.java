package dev.showhub.health;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import org.apache.kafka.clients.admin.AdminClient;

import java.util.Properties;

@Readiness
@ApplicationScoped
public class KafkaHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder builder = HealthCheckResponse.named("kafka");
        try (AdminClient client = AdminClient.create(props())) {
            client.listTopics().names().get(); 
            return builder.up().build();
        } catch (Exception e) {
            return builder.down()
                    .withData("error", e.getMessage())
                    .build();
        }
    }

    private Properties props() {
        Properties p = new Properties();
        p.put("bootstrap.servers", "localhost:29092"); // mesmo que no app
        p.put("request.timeout.ms", "2000");
        return p;
    }
}

package dev.showhub.show;

import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class ShowCreatedProducer {

    @Channel("show-created")
    Emitter<String> emitter;

    public Uni<Void> send(String payload) {
        return Uni.createFrom().completionStage(
            emitter.send(Record.of("show", payload).toString())
        ).replaceWithVoid();
    }
}

package dev.showhub.show;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class ShowCreatedConsumer {

    @Incoming("show-created-log")
    public void onMessage(String msg) {
        System.out.println("🎤 Evento show-created recebido: " + msg);
    }
}

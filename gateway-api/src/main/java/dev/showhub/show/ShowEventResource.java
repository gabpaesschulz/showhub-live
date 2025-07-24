package dev.showhub.show;

import dev.showhub.metrics.ShowMetrics;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Path("/shows")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShowEventResource {

    @Inject
    ShowCreatedProducer producer;

    @Inject
    ShowMetrics metrics;

    @GET
    public List<ShowEvent> list() {
        return ShowEvent.listAll();
    }

    @GET
    @Path("/{id}")
    public ShowEvent get(@PathParam("id") UUID id) {
        return ShowEvent.findById(id);
    }

    @POST
    @Transactional
    public ShowEvent create(ShowEvent dto) {
        dto.id = null;
        dto.persist();
        metrics.incrementCreated();
        producer.send("{\"id\":\""+dto.id+"\",\"name\":\""+dto.name+"\"}").subscribe().with(
            v -> {}, Throwable::printStackTrace
        );
        return dto;
    }
}

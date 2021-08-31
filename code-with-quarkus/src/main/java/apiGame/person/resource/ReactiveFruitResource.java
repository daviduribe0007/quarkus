package apiGame.person.resource;


import apiGame.person.entity.Fruit;
import apiGame.person.service.ReactiveFruitService;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/reactive_fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReactiveFruitResource {

    @Inject
    ReactiveFruitService fruitService;

    @GET
    public Uni<List<Fruit>> list() {
        return fruitService.list();
    }

    @POST
    public Uni<List<Fruit>> add(Fruit fruit) {
        return fruitService.add(fruit)
                .onItem().ignore().andSwitchTo(this::list);
    }

    @DELETE
    @Path("/id/{id}")
    public Response delete(@PathParam("id") String id) {
        fruitService.deleteFruit(id);
        return Response.ok().build();
    }
}

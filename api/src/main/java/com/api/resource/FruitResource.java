package com.api.resource;

import com.api.collections.Fruit;
import com.api.repositories.FruitRepository;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/fruit2")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

    @Inject
    FruitRepository fruitRepository;

    @GET
    public List<Fruit> list() {
        return fruitRepository.listFruit();
    }

    @POST
    public Response add(Fruit fruit) {
        fruitRepository.createdFruit(fruit);
        return Response.ok().build();
    }

    @DELETE
    @Path("/id/{id}")
    public Response delete(@PathParam("id") Long id) {
       fruitRepository.deleteFruit(id);
        return Response.ok().build();
    }

}

package com.codewithpanache.person.resource;


import com.codewithpanache.person.entity.Person;
import com.codewithpanache.person.service.PersonService;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonService service;

    @GET
    public List<Person> list() {
        return service.listAll();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        service.deleteById(id);
    }

    @POST
    public Response create(Person person) {
        service.persist(person);
        return Response.ok().build();
    }

    @GET
    @Path("/id/{id}")
    public Person showPersonById(@PathParam("id") String id) {
        return service.findById(id);
    }

    @PUT
    @Path("/{id}")
    public Response update(Person person) {
        service.update(person);
        return Response.created(URI.create("/person/" + person.getId())).build();
    }
}

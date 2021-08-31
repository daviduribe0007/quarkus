package apiGame.person.resource;

import apiGame.person.entity.Person;
import apiGame.person.service.PersonService;
import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonService service;

    @GET
    public Uni<List<Person>> list(){
        return service.personlist();
    }

    @POST
    public Uni<List<Person>> add(Person person) {
        return service.add(person)
                .onItem().ignore().andSwitchTo(this::list);
    }


}

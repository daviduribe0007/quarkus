package com.DevNation;


import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.annotations.SseElementType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/dev")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_PLAIN})

public class ExampleDevNation {

    @GET
    public String hello(){
        return "HI";
    }

    @GET
    @Path("/greeting")
    public Uni<String> greeting(){
        return Uni.createFrom().item("Higreeting");
    }




}

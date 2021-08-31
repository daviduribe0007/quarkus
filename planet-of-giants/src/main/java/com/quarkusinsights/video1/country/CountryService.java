//package com.quarkusinsights.video1.country;
//
//import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
//import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import java.util.List;
//
//@Path("/v2")
//@RegisterRestClient
//@ApplicationScoped
//public interface CountryService {
//
//    @GET
//    @Produces("application/json")
//    @Path("/name/{name}")
//    List<Country> getByName( @PathParam("name") String name) ;
//
//}

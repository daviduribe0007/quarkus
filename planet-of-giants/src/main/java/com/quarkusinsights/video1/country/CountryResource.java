//package com.quarkusinsights.video1.country;
//
//import io.smallrye.mutiny.Uni;
//import org.eclipse.microprofile.rest.client.inject.RestClient;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import java.util.List;
//
//@Path("/country")
//@Produces(MediaType.APPLICATION_JSON)
//public class CountryResource {
//
//    private final CountryService countryService;
//
//
//    public CountryResource(@RestClient CountryService countryService) {
//        this.countryService = countryService;
//    }
//
//    @GET
//    @Path("/name/{name}")
//    public List<Country> getByName(@PathParam("name") String name) {
//        return countryService.getByName(name);
//    }
//
//
//
//
//    /*
//    * example
//    localhost:8080/country/name/greece
//    * */
//
//}

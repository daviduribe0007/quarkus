//package com.reactivesummitvideo;
//
//import io.smallrye.mutiny.Uni;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//
//@Path("/super")
//@Produces("application/json")
//public class SuperResource {
//
//
//    @GET
//    public Uni<String> greeting() {
//        return Uni.createFrom().item("SuperMan") ;
//    }
//
//    @GET
//    @Path("/hero" )
//    public Uni<Hero> hero(){
//        return Hero.findRamdom();
//    }
//    @GET
//    @Path("/villain" )
//    public Uni<Villain> villain(){
//        return Villain.findRamdom();
//    }
//
//
//
//
//}

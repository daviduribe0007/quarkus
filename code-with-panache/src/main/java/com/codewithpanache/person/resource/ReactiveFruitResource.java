//package com.codewithpanache.person.resource;
//
//import com.codewithpanache.person.entity.Fruit;
//import com.codewithpanache.person.service.ReactiveFruitService;
//import io.smallrye.mutiny.Uni;
//
//import javax.inject.Inject;
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import java.util.List;
//
//import static com.mongodb.client.model.Filters.and;
//import static com.mongodb.client.model.Filters.eq;
//
//@Path("/fruit")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//public class ReactiveFruitResource {
//
//    @Inject
//    ReactiveFruitService fruitService;
//
//    @GET
//    public Uni<List<Fruit>> list() {
//        return fruitService.listAll();
//    }
//
//    @DELETE
//    @Path("/{id}")
//    public Uni<Boolean> delete(@PathParam("id") String id) {
//
//        return fruitService.deleteFruit(id);
//    }
//
//    @POST
//    public Uni<Fruit> create(Fruit fruit) {
//        return fruitService.persistFruit(fruit);
//    }
//
////    @GET
////    @Path("{id}")
////    public Uni<Fruit> showFruitById(@PathParam("id") String id) {
////        return fruitService.findById(String.valueOf(new ObjectId(id)));
////    }
//////
////    @PUT
////    public Uni<Fruit> update(Fruit fruit) {
////        return fruitService.updateFruit(fruit);
////    }
//}

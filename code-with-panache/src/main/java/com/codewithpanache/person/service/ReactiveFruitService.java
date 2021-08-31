//package com.codewithpanache.person.service;
//
//
//import com.codewithpanache.person.entity.Fruit;
//import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepositoryBase;
//import io.smallrye.mutiny.Multi;
//import io.smallrye.mutiny.Uni;
//
//import javax.enterprise.context.ApplicationScoped;
//
//@ApplicationScoped
//public class ReactiveFruitService implements ReactivePanacheMongoRepositoryBaseReactivePanacheMongoRepositoryBaseReactivePanacheMongoRepositoryBase<Fruit,String> {
//
//
//    public Uni<Fruit> persistFruit(Fruit fruit) {
//        return persist(fruit);
//    }
//
//
//    public Multi<Fruit> listAllfruits() {
//        return streamAll();
//    }
//
//
//    public Uni<Fruit> updateFruit(Fruit fruit) {
//        return update(fruit);
//    }
//
//    public Uni<Boolean> deleteFruit(String id) {
//       return  deleteById(id);
//    }
//
//
//
////    @Override
////    public Uni<Optional<Fruit>> findByIdOptional(Object id) {
////        return ReactivePanacheMongoRepositoryBase.super.findByIdOptional(id);
////    }
////
////
////    public Uni<Boolean> deleteFruit(String id) {
////        return deleteById(id);
////    }
//
//
//}
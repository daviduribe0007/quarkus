//package com.reactivesummitvideo;
//
//import io.quarkus.mongodb.panache.MongoEntity;
//import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
//import io.smallrye.mutiny.Uni;
//
//import java.util.Random;
//
//@MongoEntity(collection = "heroes")
//public class Hero extends ReactivePanacheMongoEntity {
//
//    public String name;
//    public int level;
//
//
//    public static Uni<Hero> findRamdom() {
//        Random ramdom = new Random();
//
//        return Hero.count()
//                .onItem().transform(x -> ramdom.nextInt(x.intValue()))
//                .onItem().transformToUni(index -> {
//              return Hero.findAll().page(index, 1).firstResult();
//        });
//
//    }
//}

//package com.reactivesummitvideo;
//
//import io.quarkus.mongodb.panache.MongoEntity;
//import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
//import io.smallrye.mutiny.Uni;
//
//import java.util.Random;
//
//@MongoEntity(collection = "villains")
//public class Villain  extends ReactivePanacheMongoEntity {
//
//    public String name;
//    public int level;
//
//
//    public static Uni<Villain> findRamdom() {
//        Random ramdom = new Random();
//
//        return Villain.count()
//                .onItem().transform(x -> ramdom.nextInt(x.intValue()))
//                .onItem().transformToUni(index -> {
//              return Villain.findAll().page(index, 1).firstResult();
//        });
//
//    }
//}

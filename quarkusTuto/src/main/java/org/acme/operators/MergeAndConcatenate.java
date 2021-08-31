package org.acme.operators;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.subscription.Cancellable;

import java.time.Duration;

public class MergeAndConcatenate {

    public void mergeAndConcatenate(){

//    Merging Multis

//    Multi<T> multi1 = getFirstMulti();
//    Multi<T> multi2 = getSecondMulti();
//
//    Multi<T> merged = Multi.createBy().merging().streams(multi1, multi2);

//        Multi<String> first = Multi.createFrom().ticks().every(Duration.ofMillis(10))
//                .onItem().transform(l -> "Stream 1 - " + l);
//
//        Multi<String> second = Multi.createFrom().ticks().every(Duration.ofMillis(15))
//                .onItem().transform(l -> "Stream 2 - " + l);
//
//        Multi<String> third = Multi.createFrom().ticks().every(Duration.ofMillis(5))
//                .onItem().transform(l -> "Stream 3 - " + l);
//
//        Cancellable cancellable = Multi.createBy().merging().streams(first, second, third)
//                .subscribe().with(s -> System.out.println("Got item: " + s));

//        //concatenate multi
//        Multi<T> multi1 = getFirstMulti();
//        Multi<T> multi2 = getSecondMulti();
//
//        Multi<T> concatenated = Multi.createBy().concatenating().streams(multi1, multi2);

        Multi<String> first2 = Multi.createFrom().items("A1", "A2", "A3");
        Multi<String> second2 = Multi.createFrom().items("B1", "B2", "B3");

        Multi.createBy().concatenating().streams(first2, second2)
                .subscribe().with(item -> System.out.print(item)); // "A1A2A3B1B2B3"
        System.out.println("");

        Multi.createBy().concatenating().streams(second2, first2)
                .subscribe().with(item -> System.out.print(item)); // "B1B2B3A1A2A3"


    }



}


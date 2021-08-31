package org.acme.starting;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.util.Locale;

public class TransformingItems {

    public void transform(){

        //item to uni
        Uni<String> uni44 = Uni.createFrom().item("hello");

        //Transforming items produced by a Multi
        uni44
                .onItem().transform(i -> i.toUpperCase())
                .subscribe().with(
                item -> System.out.println(item));


        //item to multi
        Multi<String> multi = Multi.createFrom().items("a", "b", "c");
        multi
                .onItem().transform(i -> i.toUpperCase())
                .subscribe().with(
                item -> System.out.println(item));

        //Chaining multiple transformations

        Uni<String> u = uni44
                .onItem().transform(i -> i.toLowerCase(Locale.ROOT))
                .onItem().transform(i -> i + "!");
        uni44.subscribe().with(
                item -> System.out.println(item));




    }



}

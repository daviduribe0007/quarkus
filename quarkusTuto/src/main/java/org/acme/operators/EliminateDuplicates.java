package org.acme.operators;

import io.smallrye.mutiny.Multi;

import java.util.List;

public class EliminateDuplicates {

    public void eliminateDuplicates() {
//YOU CANT USE THIS ON FLOWS
        Multi<Integer> numbers = Multi.createFrom().items(10, 10, 10, 2, 2, 30, 6, 50, 5, 8, 10, 1, 5, 8);

        List<Integer> list = numbers
                .select().distinct()
                .collect().asList()
                .await().indefinitely();

        System.out.println(list);
        System.out.println("");

        //Skipping repetitions after and before the number USED ON FLOWS
        List<Integer> list2 = numbers
                .skip().repetitions()
                .collect().asList()
                .await().indefinitely();

        System.out.println(list2);
    }
}

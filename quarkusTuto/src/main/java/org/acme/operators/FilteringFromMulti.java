package org.acme.operators;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.util.List;

public class FilteringFromMulti {

    public void filter() {

        Multi<Integer> numbers = Multi.createFrom().items(10, 2, 30, 6, 50, 5, 8, 10);


        List<Integer> list = numbers
                .select().where(i -> i > 6)
                .collect().asList()
                .await().indefinitely();

        System.out.println(list);

        Multi<Integer> numbers2 = numbers
                .filter(integer -> integer%2 ==0);

        numbers2.subscribe().with(System.out::println);

        List<Integer> list2 = numbers
                .select().when(i -> Uni.createFrom().item(i > 6))
                .collect().asList()
                .await().indefinitely();




    }
}

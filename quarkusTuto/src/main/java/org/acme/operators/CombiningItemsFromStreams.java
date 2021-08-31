package org.acme.operators;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.tuples.Tuple2;

import javax.ws.rs.core.Response;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class CombiningItemsFromStreams {

    public void combining(){

        // Combining Unis the limit is 10 UNIS

        Uni<Integer> uniA = Uni.createFrom().item(10);
        Uni<Integer> uniB = Uni.createFrom().item(20);

        Uni<Tuple2<Integer, Integer>> responses = Uni.combine()
                .all().unis(uniA, uniB).asTuple();

        responses.subscribe().with(System.out::println);
        responses.subscribe().with(tuple -> {
            System.out.println("Response from A: " + tuple.getItem1());
            System.out.println("Response from B: " + tuple.getItem2());
        });

        //Uni to map

        Uni<Map<String, Integer>> uni = Uni.combine()
                .all().unis(uniA, uniB).combinedWith(
                        listOfResponses -> {
                            Map<String, Integer> map = new LinkedHashMap<>();
                            map.put("A", 7);
                            map.put("B", 6);
                            return map;
                        }
                );

        uni.subscribe().with(System.out::println);

        //Combining Multi

        Multi<Integer> numbers = Multi.createFrom().items(1, 2, 3, 4, 5);
        Multi<String> numbers2 = Multi.createFrom().items("one", "two","three", "four", "five");
        Multi<Integer> procese = numbers.onItem().transform(i -> i * 2);

        Multi<Tuple2<Integer, String>> combined = Multi.createBy().combining()
                .streams(procese, numbers2).asTuple();


        combined.subscribe().with(System.out::println);

        /* For the limit of 10 you can use this
        Multi.createBy().combining()
                .streams(numbers, numbers2).using(list -> combineItems(list))
                .subscribe().with(x -> {
            System.out.println(x);
        });
        */


//        Combining the latest items with the other multi

        Multi<?> multi1 = Multi.createBy().combining()
                .streams(numbers2, numbers)
                .latestItems().asTuple()
                .onItem().transform(objects ->
        {
            System.out.println(objects.getItem1().toUpperCase(Locale.ROOT) +" per "+ objects.getItem2() + " = ");
            System.out.println(objects.getItem2()*5);
            return "";
        });

        multi1.subscribe().with(System.out::println);

        // or
/*
        Multi<String> multi2 = Multi.createBy().combining()
                .streams(numbers, numbers2)
                .latestItems().using(list -> combineItems(list));
*/




    }
}

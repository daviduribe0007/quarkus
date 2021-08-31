package org.acme.operators;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.time.Duration;
import java.util.Random;

public class Delay {
    public void delay() {

        Multi<Integer> multi = Multi.createFrom().items(1, 20, 3, 4, 5);

        Uni<String> delayed = Uni.createFrom().item("hello")
                .onItem().delayIt().by(Duration.ofSeconds(3));

        delayed.subscribe().with(System.out::println);


//        Uni<String> delayed = Uni.createFrom().item("hello")
//                // The write method returns a Uni completed
//                // when the operation is done.
//                .onItem().delayIt().until(this::write);

        //Throttling a Multi

        Multi<Integer> delayed2 = multi
                .onItem().call(i ->
                        // Delay the emission until the returned uni emits its item
                        Uni.createFrom().nullItem().onItem().delayIt().by(Duration.ofSeconds(1))
                );

        delayed2.subscribe().with(System.out::println);
        System.out.println("");

        //random delay
        Random random = new Random();
        Multi<Integer> delayed3 = Multi.createFrom().items(1, 2, 3, 4, 5)
                .onItem().call(i -> {
                    Duration delay = Duration.ofMillis(random.nextInt(100) + 1);
                    return Uni.createFrom().nullItem().onItem().delayIt().by(delay);
                });

        delayed3.subscribe().with(System.out::print);

        // Introduce a one second delay between each item
        Multi<Long> ticks = Multi.createFrom().ticks().every(Duration.ofSeconds(20))
                .onOverflow().drop();
        Multi<Integer> delayed4 = Multi.createBy().combining().streams(ticks, multi)
                .using((x, item) -> item);

        delayed4.subscribe().with(System.out::print);

        

    }
}

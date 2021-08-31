package org.acme.starting;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.subscription.Cancellable;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class First {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Welcome";
    }

    public void all() {
        //hi world
        Uni.createFrom().item("Welcome")
                .onItem().transform(item -> item + " to POG")
                .onItem().transform(String::toUpperCase)
                .subscribe().with(
                item -> System.out.println(">>" + item));

        //Mutiny uses a builder API
        Uni<String> uni1 = Uni.createFrom().item("hello");
        Uni<String> uni2 = uni1.onItem().transform(item -> item + " mutiny");
        Uni<String> uni3 = uni2.onItem().transform(String::toUpperCase);

        uni3.subscribe().with(item -> System.out.println("222 " + item));


        Uni<String> uni = Uni.createFrom().item("hello");

        uni.onItem().transform(item -> item + " mutiny");
        uni.onItem().transform(String::toUpperCase);

        uni.subscribe().with(item -> System.out.println(">> " + item));

        //The Uni type
        Uni.createFrom().item(1)
                .onItem().transform(i -> "hello-" + i)
                .onItem().delayIt().by(Duration.ofMillis(2000))
                .subscribe().with(System.out::println);

        //Subscribing to a Uni
        Cancellable cancellable = uni
                .subscribe().with(
                        item -> System.out.println(item),
                        failure -> System.out.println("Failed with " + failure));

        //Creating Unis from item
        AtomicInteger counter = new AtomicInteger(5);
        Uni<Integer> uni22 = Uni.createFrom().item(() -> counter.get());
        System.out.println(counter);

        //Creating failing Unis
        // Pass an exception directly:
        Uni<Integer> failed1 = Uni.createFrom().failure(new Exception("boom"));

        // Pass a supplier called for every subscriber:
        Uni<Integer> failed2 = Uni.createFrom().failure(() -> new Exception("boom"));

        //Creating Uni<Void>
        Uni<Void> uninull = Uni.createFrom().nullItem();

        //Creating Unis using an emitter (advanced)
        Uni<String> uni66 = Uni.createFrom().emitter(em -> {
            // When the result is available, emit it
            em.complete("result");

        });


        //Creating Unis from a CompletionStage (advanced)
        //Uni<String> uni = Uni.createFrom().completionStage(stage);


        //The Multi type
        Multi<Integer> numbers = Multi.createFrom().items(1, 2, 3, 4, 5)
                .onItem().transform(i -> i * 2)
                .select().first(3)
                .onFailure().recoverWithItem(0);

        numbers.subscribe().with(System.out::println);


        // Uni<String> uni = Uni.createFrom().completionStage(stage);

        //this show me if the flow are complete or not
        Cancellable cancellable2 = numbers
                .subscribe().with(
                        item -> System.out.println(item),
                        failure -> System.out.println("Failed with " + failure),
                        () -> System.out.println("Completed"));


        //Creating Multi from items
        Multi<Integer> multiFromItems = Multi.createFrom().items(1, 2, 3, 4);
        Multi<Integer> multiFromIterable = Multi.createFrom().iterable(Arrays.asList(1, 2, 3, 4, 5));
        multiFromItems.subscribe().with(System.out::println);
        multiFromIterable.subscribe().with(System.out::println);

        AtomicInteger counter2 = new AtomicInteger();
        Multi<Integer> multi = Multi.createFrom().items(() ->
                IntStream.range(counter2.getAndIncrement(), counter2.get() * 7).boxed());
        multi.subscribe().with(System.out::println);

        //Creating failing Multis
        // Pass an exception directly:
        Multi<Integer> failed13 = Multi.createFrom().failure(new Exception("boom"));

        // Pass a supplier called for every subscriber:
        Multi<Integer> failed23 = Multi.createFrom().failure(() -> new Exception("boom"));

        //Creating empty Multis
        Multi<String> multi3 = Multi.createFrom().empty();

        //Creating Multis using an emitter (advanced)
        Multi<Integer> multi4 = Multi.createFrom().emitter(em -> {
            em.emit(11);
            em.emit(22);
            em.emit(33);
            em.complete();
        });
        multi4.subscribe().with(System.out::println);

        //infinite Creating Multis from ticks (advanced)
        /*
        Multi<Long> ticks = Multi.createFrom().ticks().every(Duration.ofMillis(100));
        ticks.subscribe().with(System.out::println);
        */

        multi
                .onSubscribe().invoke(() -> System.out.println("⬇️ Subscribed"))
                .onItem().invoke(i -> System.out.println("⬇️ Received item: " + i))
                .onFailure().invoke(f -> System.out.println("⬇️ Failed with " + f))
                .onCompletion().invoke(() -> System.out.println("⬇️ Completed"))
                .onCancellation().invoke(() -> System.out.println("⬆️ Cancelled"))
                .onRequest().invoke(l -> System.out.println("⬆️ Requested: " + l));

        //The call method
        multi
                .onItem().call(i ->
                Uni.createFrom().voidItem()
                        .onItem().delayIt().by(Duration.ofSeconds(1)
                )
        );
        // call is used when you need close something
        /*
        multi
                .onCompletion().call(() -> resource.close());
*/





    }
}

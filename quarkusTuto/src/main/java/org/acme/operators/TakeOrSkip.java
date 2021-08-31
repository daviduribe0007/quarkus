package org.acme.operators;

import io.smallrye.mutiny.Multi;

import java.time.Duration;

public class TakeOrSkip {

    public  void take(){


        Multi<Integer> numbers = Multi.createFrom().items(10, 2, 30, 6, 50, 5, 8, 10);

        Multi<Integer> firstThreeItems = numbers.select().first(3);


    firstThreeItems.subscribe().with(System.out::print);

        Multi<Integer> lastThreeItems = numbers.select().last(3);
        System.out.println("");

        lastThreeItems.subscribe().with(System.out::print);

        Multi<Integer> takeWhile = numbers.select().first(i -> i < 50);

        System.out.println("");

        takeWhile.subscribe().with(System.out::print);

        Multi<Integer> takeForDuration = numbers.select().first(Duration.ofMillis(1));
        System.out.println("");

        takeForDuration.subscribe().with(System.out::print);
        System.out.println("");
        System.out.println("skip 3");

        //Skipping items

        Multi<Integer> skipThreeItems = numbers.skip().first(3);

        skipThreeItems.subscribe().with(System.out::print);
        System.out.println("");
        System.out.println("skipLastThreeItems 3");
        Multi<Integer>  skipLastThreeItems = numbers.skip().last(3);
        skipLastThreeItems.subscribe().with(System.out::print);

        Multi<Integer> skipWhile = numbers.skip().first(i -> i < 40);
        System.out.println("");
        System.out.println("skip when the number is < to 40");
        skipWhile.subscribe().with(System.out::print);


        System.out.println("");
        System.out.println("this process skipt the first seconds");
        Multi<Integer> skipForDuration = numbers.skip().first(Duration.ofSeconds(1));
        skipForDuration.subscribe().with(System.out::println);


    }
}

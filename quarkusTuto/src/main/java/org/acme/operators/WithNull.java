package org.acme.operators;

import io.smallrye.mutiny.TimeoutException;
import io.smallrye.mutiny.Uni;

import javax.naming.ServiceUnavailableException;
import java.time.Duration;

public class WithNull {

    public void nullOperations(){
        //How to handle null

        Uni<Object> uni = Uni.createFrom().nullItem();
        uni.onItem().ifNull().continueWith("hello");
        uni.onItem().ifNull().switchTo(() -> Uni.createFrom().item("hello"));
        uni.onItem().ifNull().failWith(() -> new Exception("Boom!"));

        uni.subscribe().with(System.out::println);

        uni
                .onItem().ifNotNull().transform(o -> o.toString().toUpperCase())
                .onItem().ifNull().continueWith("yolo!");

        uni.subscribe().with(System.out::println);

        //with limit time and fail

        Uni<Object> uniWithTimeout = uni
                .ifNoItem().after(Duration.ofMillis(100)).fail();

        uni.subscribe().with(System.out::println);

        //before the limit time recover the flow
        Uni<Object> uniWithTimeout2 = uni
                .ifNoItem().after(Duration.ofMillis(100)).fail()
                .onFailure(TimeoutException.class).recoverWithItem("we got a timeout");

        uni.subscribe().with(System.out::println);

        //custom exception
        Uni<Object> uniWithTimeout3 = uni
                .ifNoItem().after(Duration.ofMillis(100)).failWith(() -> new ServiceUnavailableException());

        // element  or recover
        Uni<Object> uniWithTimeout5 = uni
                .ifNoItem().after(Duration.ofMillis(100)).recoverWithItem(() -> "fallback");

//        Uni<Object> uniWithTimeout4 = uni
//                .ifNoItem().after(Duration.ofMillis(100)).recoverWithUni(() -> someFallbackUni());



    }
}

package org.acme;

import org.acme.operators.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/qua")
public class ExampleResource {


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    public static void main(String[] args) {

//        First first = new First();
//        first.all();
//
//        Join join = new Join();
//        join.join();
//
//        MergeAndConcatenate mergeAndContanetane = new MergeAndConcatenate();
//        mergeAndContanetane.mergeAndConcatenate();
//
//        CollectingItemsFromMulti collectinItemsfromMulti = new CollectingItemsFromMulti();
//        collectinItemsfromMulti.collection();

//        CombiningItemsFromStreams combiningItemsFromStreams = new CombiningItemsFromStreams();
//        combiningItemsFromStreams.combining();
////
//        FilteringFromMulti filteringFromMulti = new FilteringFromMulti();
//        filteringFromMulti.filter();

//        TakeOrSkip takeOrSkip = new TakeOrSkip();
//        takeOrSkip.take();

//        EliminateDuplicates eliminateDuplicates = new EliminateDuplicates();
//        eliminateDuplicates.eliminateDuplicates();
//
//        WithNull withNull = new WithNull();
//        withNull.nullOperations();

        Delay delay = new Delay();
        delay.delay();


    }
}
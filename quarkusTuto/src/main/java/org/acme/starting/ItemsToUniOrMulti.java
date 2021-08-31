package org.acme.starting;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.reactivestreams.Publisher;

public class ItemsToUniOrMulti {

    public void toUniMulti() {

        //Uni - Transforming an item into a Uni
        //remote service is represented by Uni like this:
        Uni<String> uniName = invokeRemoteGreetingService();


        uniName
                .onItem().transformToUni(name -> invokeRemoteGreetingService())
                .subscribe().with(
                item -> System.out.println(item + " uni"), // Print "Hello Cameron",
                fail -> fail.printStackTrace()); // Print the failure stack trace

        //this is used when you need transform uni to multi
        Multi<String> result = uniName
                .onItem().transformToMulti(item -> Multi.createFrom().items(item));

        result
                .onItem().transformToMulti(item -> Multi.createFrom().items(item, item));
        result
                .subscribe().with(
                item -> System.out.println(item+ " multi")); // Called twice

        //this transform merge when the order not import and concat when you need the correct sequence
        //Multi - Transforming an item into a Uni
        Multi<String> merged = result
                .onItem().transformToUniAndMerge(name -> invokeRemoteGreetingService());

        Multi<String> concat = result
                .onItem().transformToUniAndConcatenate(name -> invokeRemoteGreetingService());

        //Multi - Transforming an item into a Multi

        Multi<String> mergedMulti = result
                .onItem().transformToMultiAndMerge(item -> someMulti(item));

        Multi<String> concatMulti = result
                .onItem().transformToMultiAndConcatenate(item -> someMulti(item));




    }

    private Publisher<? extends String> someMulti(String item) {
       //this is a empty multi but this method simulated the return of various items
         Multi<String> someMulti = Multi.createFrom().empty();
        return someMulti;
    }


    private Uni<String> invokeRemoteGreetingService() {
        return Uni.createFrom().item("Cameron,uribe");
    }
}

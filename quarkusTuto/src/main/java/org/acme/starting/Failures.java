package org.acme.starting;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.naming.ServiceUnavailableException;

public class Failures
{

    public void failures(){
        Multi<String> someMulti = Multi.createFrom().empty();
        Uni<String> someUni = Uni.createFrom().nullItem();
        Uni<String> failed1 = Uni.createFrom().failure(new Exception("something was happened"));

        //Observing failures
        Uni<String> u = someUni
                .onFailure().invoke(failure -> log(failure));

        Multi<String> m = someMulti
                .onFailure().invoke(failure -> log(failure));

        //Transforming failures
        Uni<String> u2 = failed1
                .onFailure().transform(failure ->
                        new ServiceUnavailableException("something was happened"));

        //Recovering using fallback item(s)
        Uni<String> u1 = someUni
                .onFailure().recoverWithItem("hello");

        Uni<String> u3 = someUni
                .onFailure().recoverWithItem(f -> getFallback(f));


        //Completing on failure
        Multi<String> m2 = someMulti
                .onFailure().recoverWithCompletion();


        Uni<String> u4 = someUni
                .onFailure().recoverWithUni(f -> getFallbackUni(f));

        Multi<String> m4 = someMulti
                .onFailure().recoverWithMulti(f -> getFallbackMulti(f));

    }

    private Uni<? extends String> getFallbackUni(Throwable f) {
        Uni<String> someUni = Uni.createFrom().nullItem();
        return someUni;
    }

    private Multi<? extends String> getFallbackMulti(Throwable f) {
        Multi<String> someMulti = Multi.createFrom().empty();
        return someMulti;
    }

    private String getFallback(Throwable f) {
        //This simulated the case when something was wrong and you need execute one method to recover the item object
        // or something do you need to make after this failure
        return "try to recover";
    }

    private void log(Throwable failure) {
        System.out.println(failure);
    }
}

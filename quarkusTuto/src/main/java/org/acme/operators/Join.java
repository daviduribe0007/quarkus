package org.acme.operators;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.groups.UniJoin;

import java.util.List;

public class Join {



    public void join(){
        //Joining multiple unis
        Uni<Integer> a = Uni.createFrom().item(1);
        Uni<Integer> b = Uni.createFrom().item(2);
        Uni<Integer> c = Uni.createFrom().item(3);

        //this collect all failures
        Uni<List<Integer>> res = Uni.join().all(a, b, c).andCollectFailures();

        res.subscribe().with(System.out::println);

        //this when you see a fail stop and
        res = Uni.join().all(a, b, c).andFailFast();


        //Joining on the first Uni
        //get the first uni and finish
        Uni<Integer> res2 = Uni.join().first(a, b, c).toTerminate();

        res2.subscribe().with(System.out::println);

        //get the first uni and omit the first fails
        Uni<Integer>  res3 = Uni.join().first(a, b, c).withItem();

        res3.subscribe().with(System.out::println);


        //Using a builder object

        UniJoin.Builder<Integer> builder = Uni.join().builder();

//        while (someCondition) {
//            Uni<Integer> uni = supplier.get();
//            builder.add(uni);
//        }

        Uni<List<Integer>> all = builder.joinAll().andFailFast();

        Uni<Integer> first = builder.joinFirst().withItem();


    }


}

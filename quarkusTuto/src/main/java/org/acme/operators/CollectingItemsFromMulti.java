package org.acme.operators;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectingItemsFromMulti {

    public void collection() {
        //recollect multi to list
        Multi<Integer> multi = Multi.createFrom().items(1, 2, 3, 4, 5);
        Uni<List<Integer>> uni = multi.collect().asList();
        uni.subscribe().with(System.out::println);
        //recollect multi to map
        // this need a class with key or something with key
//        Uni<Map<String, String>> uni2 =
//                multi.collect()
//                        .asMap(item -> getUniqueKeyForItem(item));

        //Collecting items into a multimap
        /*
        Multi<String> multi = getMulti();
        Uni<Map<String, Collection<String>>> uni =
        multi.collect()
                .asMultiMap(item -> getKeyForItem(item));
                */
//       //Using a custom accumulator
//        Multi<String> multi = getMulti();
//        Uni<MyCollection> uni = multi.collect()
//                .in(MyCollection::new, (col, item) -> col.add(item));
        Uni<Long> count = multi.collect()
                .with(Collectors.counting());
        count.subscribe().with(System.out::println);


        //Getting the first and last items
        Uni<Integer> first2 = multi.collect().first();
        Uni<Integer> last2 = multi.collect().last();

        first2.subscribe().with(System.out::println);
        last2.subscribe().with(System.out::println);



    }
}

package org.acme.operators;public class ExampleAll {


    @GET
    @Path("/createMultisUnis")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<String> createMultisUnis() {

        return Multi.createFrom().items(1,2,3,4)
                .onItem().transform(i -> "hello-" + i)
                .map(i -> {
                    out.println(i);
                    return i;
                });
    }

    @GET
    @Path("/events")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<String> events() {

        return Multi.createFrom().items(1,2,3,4)
                .onSubscribe().invoke(() -> System.out.println("Subscribed"))
                .onItem().invoke(i -> out.println("⬇️ Received item: " + i))
                .onFailure().invoke(f -> out.println("⬇️ Failed with " + f))
                .onCompletion().invoke(() -> out.println("⬇️ Completed"))
                .onCancellation().invoke(() -> out.println("⬆️ Cancelled"))
                .onRequest().invoke(l -> out.println("⬆️ Requested: " + l))
                .map(i -> {
                    out.println(i);
                    return "hello-" + i;
                });
    }

    @GET
    @Path("/transform")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<String> transform() {

        return Multi.createFrom().items("a", "b", "c")
                .onItem().transform(i -> i.toUpperCase())
                .onItem().transform(i -> i + "!");
    }


    @GET
    @Path("/transformToMulti")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<String> transformToMulti() {

        return Uni.createFrom().item("Hello")
                .onItem().transformToMulti(prefix -> this.persons(prefix))
                .onItem().transform(saludo -> saludo + "!");
    }



    private Multi<String> persons(String prefix){
        return Multi.createFrom().items("Maria", "Isabel", "Pedro")
                .onItem().transform(name -> prefix + " " + name);
    }


    @GET
    @Path("/transformToUniAndMerge")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<String> transformToUniAndMerge() {

        return Multi.createFrom().items("Maria", "Isabel", "Vanessa")
                .onItem().transformToUniAndMerge(name -> this.hello(name));
    }


    private Uni<String> hello(String name) {

        if (name.equals("Isabel")) {
            return Uni.createFrom().item("Hello " + name).onItem().delayIt().by(Duration.ofSeconds(3));
        }

        return Uni.createFrom().item("Hello " + name);
    }

    @GET
    @Path("/failure")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<String> failure() {

        return Multi.createFrom().items("Maria", "Isabel", "Vanessa")
                .onItem().transformToUniAndMerge(name -> this.helloFailure(name))
                // .onFailure().transform(i -> {
                //     System.out.println("PAso por la conversion");
                //     return new IllegalAccessException(i.getMessage());
                // })
                .onFailure().retry().atMost(2);
    }


    private Uni<String> helloFailure(String name) {

        System.out.println("Paso por la conversion " + name);
        if (name.equals("Isabel")) {
            return Uni.createFrom().failure(new IllegalArgumentException("queroso"));
        }

        return Uni.createFrom().item("Hello " + name);
    }


    @GET
    @Path("/merging")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<String> merging() {

        Multi<String> first = Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                .onItem().transform(l -> "Stream 1 - " + l);

        Multi<String> second = Multi.createFrom().ticks().every(Duration.ofSeconds(2))
                .onItem().transform(l -> "Stream 2 - " + l);

        Multi<String> third = Multi.createFrom().ticks().every(Duration.ofSeconds(3))
                .onItem().transform(l -> "Stream 3 - " + l);


        return Multi.createBy().merging().streams(first, second, third)
                .onCancellation().invoke(() -> out.println("⬆️ Cancelled"))
                .map(i -> {
                    System.out.println("hola: " + i);
                    return i;
                });
    }

    @GET
    @Path("/collecting")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Integer> collecting() {


        return Multi.createFrom().items(1, 2, 3, 4, 5, 6, 7).onItem().transform(i -> i + " queroso")
                .collectItems().asList().onItem().transform(i -> i.size());
    }


    @GET
    @Path("/combining")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Integer> combining() {

        Uni<String> uniA = invokeHttpServiceA();
        Uni<String> uniB = invokeHttpServiceB();

        Uni.combine().all().unis(uniA, uniB).asTuple()
                .subscribe().with(tuple -> {
            System.out.println("Response from A: " + tuple.getItem1());
            System.out.println("Response from B: " + tuple.getItem2());
        });



        return Multi.createFrom().items(1, 2, 3, 4, 5, 6, 7)
                .onItem().transform(i -> i + " queroso")
                .collectItems().asList().onItem().transform(i -> i.size());
    }

    private Uni<String> invokeHttpServiceB() {
        System.out.println("se lanzo el metodo B");
        return Uni.createFrom().item("Hola").onItem().delayIt().by(Duration.ofSeconds(2));
    }

    private Uni<String> invokeHttpServiceA() {
        System.out.println("se lanzo el metodo A");
        return Uni.createFrom().item("Queroso").onItem().delayIt().by(Duration.ofSeconds(2));
    }


    @GET
    @Path("/filtering")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Integer> filtering() {

        Multi<Integer> multi = Multi.createFrom().items(7, 5);

        return multi
                .filter(i -> i > 5)
                .onItem().transform(i -> i + " queroso")

                .collectItems().asList().onItem().transform(i -> i.size());
    }
}

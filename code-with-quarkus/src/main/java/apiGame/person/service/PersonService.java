package apiGame.person.service;

import apiGame.person.entity.Fruit;
import apiGame.person.entity.Person;
import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.quarkus.mongodb.reactive.ReactiveMongoCollection;
import io.smallrye.mutiny.Uni;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class PersonService {

    @Inject
    ReactiveMongoClient mongoClient;

    public Uni<List<Person>> personlist() {
        return getCollection().find()
                .map(document -> {
                    Person person = new Person();
                    person.setId(document.getString("id"));
                    person.setName(document.getString("name"));
                    person.setAge(document.getInteger("age"));
                    return person;
                }).collect().asList();

    }

    public Uni<Void> add(Person person) {
        Document document = new Document()
                .append("id", person.getId())
                .append("name", person.getName())
                .append("age", person.getAge());
        return getCollection().insertOne(document)
                .onItem().ignore().andContinueWithNull();
    }

    private ReactiveMongoCollection<Document> getCollection() {
        return mongoClient.getDatabase("players").getCollection("person");
    }
}

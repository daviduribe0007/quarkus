package apiGame.person.service;

import apiGame.person.entity.Fruit;
import com.mongodb.client.result.DeleteResult;
import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.quarkus.mongodb.reactive.ReactiveMongoCollection;
import io.smallrye.mutiny.Uni;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

@ApplicationScoped
public class ReactiveFruitService {

    @Inject
    ReactiveMongoClient mongoClient;

    private static final Logger LOG = Logger.getLogger(String.valueOf(ReactiveFruitService.class));

    public Uni<List<Fruit>> list() {
        return getCollection().find()
                .map(doc -> {
                    Fruit fruit = new Fruit();
                    fruit.setId(doc.getString("id"));
                    fruit.setName(doc.getString("name"));
                    fruit.setDescription(doc.getString("description"));
                    return fruit;
                }).collect().asList();
    }

    public Uni<Void> add(Fruit fruit) {
        Document document = new Document()
                .append("id",fruit.getId())
                .append("name", fruit.getName())
                .append("description", fruit.getDescription());
        LOG.info("add");
        return getCollection().insertOne(document)
                .onItem().ignore().andContinueWithNull();
    }

    public Uni<DeleteResult> deleteFruit(String id){
        return mongoClient.getDatabase("players")
                .getCollection("fruit")
                .deleteOne(and(eq("id",id)));
    }



    private ReactiveMongoCollection<Document> getCollection() {
        return mongoClient.getDatabase("players").getCollection("fruit");
    }
}
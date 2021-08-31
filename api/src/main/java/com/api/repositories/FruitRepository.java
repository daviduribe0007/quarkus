package com.api.repositories;

import com.api.collections.Fruit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;


@ApplicationScoped
public class FruitRepository {


    @Inject
    EntityManager entityManager;

    @Transactional
    public void createdFruit(Fruit fruit) {
        entityManager.persist(fruit);

    }

    @Transactional
    public void deleteFruit(Long idLong id) {
         entityManager.createQuery("DELETE from Fruit f WHERE f.id = " + id).executeUpdate();

    }

    @Transactional
    public List<Fruit> listFruit() {
        List<Fruit> list = entityManager.createQuery("select fruit from Fruit fruit").getResultList();
        return list;
    }
}

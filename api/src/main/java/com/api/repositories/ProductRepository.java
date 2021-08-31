package com.api.repositories;

import com.api.collections.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProductRepository {
    @Inject
    EntityManager em;

    @Transactional
    public void createdProduct(Product p){
        em.persist(p);

    }

    @Transactional
    public void deleteProduct(Long id){
        em.createQuery("DELETE from Product p WHERE p.id = " + id).executeUpdate();

    }

    @Transactional
    public List<Product> listProduct(){
        List<Product> products = em.createQuery("select p from Product p").getResultList();
        return products;
    }
}

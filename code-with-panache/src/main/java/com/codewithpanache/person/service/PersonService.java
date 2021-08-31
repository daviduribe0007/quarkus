package com.codewithpanache.person.service;


import com.codewithpanache.person.entity.Person;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonService  implements PanacheMongoRepositoryBase<Person,String> {


}

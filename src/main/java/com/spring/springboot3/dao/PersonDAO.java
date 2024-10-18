package com.spring.springboot3.dao;

import com.spring.springboot3.collection.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;

@Component
public class PersonDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Person findByEmail(String email) {
        Criteria criteria = Criteria.where("email").is(email);
        Aggregation aggregation = Aggregation.newAggregation(match(criteria));
        return mongoTemplate.aggregate(aggregation, "person", Person.class).getMappedResults().get(0);
    }

    public List<Person> findByName(String name) {
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("first_name").regex(name, "i"),
                Criteria.where("last_name").regex(name, "i"),
                Criteria.where("middle_name").regex(name, "i"));
        Aggregation aggregation = Aggregation.newAggregation(match(criteria));
        return mongoTemplate.aggregate(aggregation, "person", Person.class).getMappedResults();
    }
}

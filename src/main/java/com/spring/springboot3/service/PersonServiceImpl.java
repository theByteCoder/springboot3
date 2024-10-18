package com.spring.springboot3.service;

import com.spring.springboot3.collection.Person;
import com.spring.springboot3.dao.PersonDAO;
import com.spring.springboot3.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public String insertPerson(Person person) {
        return personRepository
                .insert(person)
                .getId();
    }

    @Override
    public List<String> insertPersons(List<Person> persons) {
        return personRepository
                .insert(persons)
                .stream()
                .map(person -> person.getId()).toList();
    }

    @Override
    public Person getPersonByEmail(String email) {
        return personDAO.findByEmail(email);
    }

    @Override
    public List<Person> getPersonByName(String name) {
        return personDAO.findByName(name);
    }
}

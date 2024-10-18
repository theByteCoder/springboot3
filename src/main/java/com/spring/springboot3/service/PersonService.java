package com.spring.springboot3.service;

import com.spring.springboot3.collection.Person;

import java.util.List;

public interface PersonService {

    public String insertPerson(Person person);

    public List<String> insertPersons(List<Person> persons);

    public Person getPersonByEmail(String email);

    public List<Person> getPersonByName(String name);
}

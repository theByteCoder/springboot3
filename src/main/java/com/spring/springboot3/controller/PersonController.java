package com.spring.springboot3.controller;

import com.spring.springboot3.collection.Person;
import com.spring.springboot3.dto.PersonRequestDTO;
import com.spring.springboot3.service.PersonService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private ObservationRegistry observationRegistry;

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<String> createPerson(@RequestBody PersonRequestDTO requestDTO) {
        Person person = requestDTO.toPerson();
        return Observation.createNotStarted("createPerson", observationRegistry)
                .observe(() -> ResponseEntity.ok(personService.insertPerson(person)));
    }

    @GetMapping("/email")
    public ResponseEntity<Person> getPersonsByEmail(@RequestParam String search) {
        return Observation.createNotStarted("getPersonsByEmail", observationRegistry)
                .observe(() -> ResponseEntity.ok(personService.getPersonByEmail(search)));
    }

    @GetMapping("/name")
    public ResponseEntity<List<Person>> getPersonsByName(@RequestParam String search) {
        return Observation.createNotStarted("getPersonsByName", observationRegistry)
                .observe(() -> ResponseEntity.ok(personService.getPersonByName(search)));
    }
}

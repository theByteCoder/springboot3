package com.spring.springboot3.dto;

import com.spring.springboot3.collection.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequestDTO {

    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String email;

    public Person toPerson() {
        return Person.builder()
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .contact(Person.Contact.builder().phone(phoneNumber).email(email).build())
                .build();
    }
}

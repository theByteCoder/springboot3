package com.spring.springboot3.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "person")
public class Person {

    @MongoId
    private String id;

    @NotNull
    @Field(name = "first_name")
    private String firstName;

    @Field(name = "middle_name")
    private String middleName;

    @NotNull
    @Field(name = "last_name")
    private String lastName;

    private Contact contact;

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Contact {

        @Indexed(unique = true)
        private String phone;

        @Indexed(unique = true)
        private String email;
    }
}

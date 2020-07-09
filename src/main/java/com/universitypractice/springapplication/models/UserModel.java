package com.universitypractice.springapplication.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserModel {

    private Long id;

    private final String username;

    private final String firstName;

    private final String lastName;
    private final String  gender;
    private final Integer age;
    private final String email;

    public UserModel(String username, String firstName, String lastName,
                     String gender, Integer age, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = (gender != null) ? gender.toLowerCase() : null;
        this.age = age;
        this.email = email;
    }
}

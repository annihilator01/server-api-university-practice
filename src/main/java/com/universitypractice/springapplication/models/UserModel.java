package com.universitypractice.springapplication.models;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserModel {

    private Long id;

    @NonNull
    private final String username;

    @NonNull
    private final String firstName;

    private final String lastName;
    private final String  gender;
    private final Integer age;
    private final String email;

    public UserModel(@NonNull String username, @NonNull String firstName, String lastName,
                     String gender, Integer age, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender.toLowerCase();
        this.age = age;
        this.email = email;
    }
}

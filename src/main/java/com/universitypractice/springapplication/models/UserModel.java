package com.universitypractice.springapplication.models;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserModel {

    private Long id;

    @NonNull
    private String userName;

    @NonNull
    private String firstName;

    private String lastName;
    private String gender;
    private Integer age;
    private String email;
}

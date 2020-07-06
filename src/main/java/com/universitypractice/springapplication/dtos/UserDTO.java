package com.universitypractice.springapplication.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserDTO {

    private Long id;

    @NonNull
    private String username;

    @NonNull
    private final String firstName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String lastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String gender;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Integer age;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String email;

    public UserDTO(@NonNull String username, @NonNull String firstName, String lastName,
                   String gender, Integer age, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
    }
}

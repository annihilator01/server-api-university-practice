package com.universitypractice.springapplication.dtos;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserDTO {

    private Long id;

    @NonNull
    private String username;

    @NonNull
    private String firstName;

    private String lastName;
    private String gender;
    private Integer age;
    private String email;
}

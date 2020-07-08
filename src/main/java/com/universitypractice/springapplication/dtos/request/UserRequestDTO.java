package com.universitypractice.springapplication.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String username;

    private String firstName;

    private String lastName;

    private String gender;

    private Integer age;

    private String email;
}

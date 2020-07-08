package com.universitypractice.springapplication.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    @NonNull
    private Long id;

    @NonNull
    private String username;

    @NonNull
    private String firstName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String gender;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer age;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
}

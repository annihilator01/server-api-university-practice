package com.universitypractice.springapplication.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class UserStatusesModel {

    @NonNull
    private final Long id;

    @NonNull
    private final String username;

    @NonNull
    private final String firstName;

    @NonNull
    private final String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Long lastStatusChangedTime;
}

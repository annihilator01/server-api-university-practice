package com.universitypractice.springapplication.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeStatusResponseDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @NonNull
    private Long userId;

    @NonNull
    private String oldStatus;

    @NonNull
    private String newStatus;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long statusChangedTime;
}

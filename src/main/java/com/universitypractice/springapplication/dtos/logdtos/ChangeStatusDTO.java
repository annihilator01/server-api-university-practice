package com.universitypractice.springapplication.dtos.logdtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ChangeStatusDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    private Long userId;

    private String oldStatus;

    @NonNull
    private String newStatus;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long statusChangedTime;

    public void setResponseParams(Long id, Long userId, String oldStatus, Long statusChangedTime) {
        this.id = id;
        this.userId = userId;
        this.oldStatus = oldStatus;
        this.statusChangedTime = statusChangedTime;
    }
}

package com.universitypractice.springapplication.models.logmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ChangeStatusModel {

    private Long id;

    @NonNull
    private Long userId;

    private String oldStatus;

    @NonNull
    private final String newStatus;

    private Long statusChangedTime;

    public ChangeStatusModel(Long userId, String newStatus) {
        this.userId = userId;
        this.newStatus = (newStatus != null) ? newStatus.toLowerCase() : null;
    }
}

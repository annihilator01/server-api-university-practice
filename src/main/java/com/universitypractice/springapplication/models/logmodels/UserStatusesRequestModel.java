package com.universitypractice.springapplication.models.logmodels;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserStatusesRequestModel {

    private Long id;

    private Long requestTimestamp;

    private final String statusFilter;

    private final Long changedAfterTimestamp;

    public UserStatusesRequestModel(String statusFilter, Long changedAfterTimestamp) {
        this.statusFilter = (statusFilter != null) ? statusFilter.toLowerCase() : null;
        this.changedAfterTimestamp = changedAfterTimestamp;
    }

    public void setResponseParams(Long id, Long requestTimestamp) {
        this.id = id;
        this.requestTimestamp = requestTimestamp;
    }
}

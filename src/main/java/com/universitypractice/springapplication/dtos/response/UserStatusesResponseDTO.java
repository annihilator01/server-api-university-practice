package com.universitypractice.springapplication.dtos.response;

import com.universitypractice.springapplication.models.UserStatusesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStatusesResponseDTO {

    @NonNull
    private Long requestId;

    @NonNull
    private Long requestTimestamp;

    private String statusFilter;

    private Long changedAfterTimestamp;

    @NonNull
    private List<UserStatusesModel> userStatusesModels;
}

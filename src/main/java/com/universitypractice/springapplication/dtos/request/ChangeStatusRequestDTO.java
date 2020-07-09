package com.universitypractice.springapplication.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangeStatusRequestDTO {
    private String newStatus;
}

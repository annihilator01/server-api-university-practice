package com.universitypractice.springapplication.services.interfaces.operations;

import com.universitypractice.springapplication.entities.StatusEntity;
import com.universitypractice.springapplication.enums.Status;

public interface StatusEntityGettingService extends EntityGettingService<StatusEntity, Status> {
    StatusEntity getByString(String status);
}

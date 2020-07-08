package com.universitypractice.springapplication.services.interfaces.entityoperations;

import com.universitypractice.springapplication.entities.StatusEntity;
import com.universitypractice.springapplication.enums.Status;

public interface GetStatusEntityService extends GetEntityService<StatusEntity, Status> {
    StatusEntity getByString(String status);
}

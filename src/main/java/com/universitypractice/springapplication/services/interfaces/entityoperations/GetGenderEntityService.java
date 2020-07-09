package com.universitypractice.springapplication.services.interfaces.entityoperations;

import com.universitypractice.springapplication.entities.GenderEntity;
import com.universitypractice.springapplication.enums.Gender;

public interface GetGenderEntityService extends GetEntityService<GenderEntity, Gender> {
    GenderEntity getByString(String gender);
}

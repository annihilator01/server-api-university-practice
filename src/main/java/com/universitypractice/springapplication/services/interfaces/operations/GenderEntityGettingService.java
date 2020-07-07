package com.universitypractice.springapplication.services.interfaces.operations;

import com.universitypractice.springapplication.entities.GenderEntity;
import com.universitypractice.springapplication.enums.Gender;

public interface GenderEntityGettingService extends EntityGettingService<GenderEntity, Gender> {
    GenderEntity getByString(String gender);
}

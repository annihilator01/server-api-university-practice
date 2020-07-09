package com.universitypractice.springapplication.services.interfaces.entityoperations;

import com.universitypractice.springapplication.entities.interfaces.JPAEntity;
import com.universitypractice.springapplication.services.interfaces.baseoperations.GetService;

public interface GetEntityService<T extends JPAEntity, F> extends GetService<T, F> {

}

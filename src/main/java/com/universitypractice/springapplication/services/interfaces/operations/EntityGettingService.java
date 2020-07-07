package com.universitypractice.springapplication.services.interfaces.operations;

import com.universitypractice.springapplication.entities.interfaces.JPAEntity;

public interface EntityGettingService<T extends JPAEntity, ID> extends GetService<T, ID> {

}

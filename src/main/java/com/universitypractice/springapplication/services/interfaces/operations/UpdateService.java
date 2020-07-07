package com.universitypractice.springapplication.services.interfaces.operations;

public interface UpdateService<T, ID> {
    T update(ID id, T updateInfo);
}

package com.universitypractice.springapplication.services.interfaces.operations;

public interface GetService<T, ID> {
    T get(ID id);
}

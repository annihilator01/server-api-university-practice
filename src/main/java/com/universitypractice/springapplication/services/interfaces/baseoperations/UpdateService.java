package com.universitypractice.springapplication.services.interfaces.baseoperations;

public interface UpdateService<T, F> {
    T update(F filter, T updateInfo);
}

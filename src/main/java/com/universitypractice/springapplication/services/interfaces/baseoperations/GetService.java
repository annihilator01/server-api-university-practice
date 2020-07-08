package com.universitypractice.springapplication.services.interfaces.baseoperations;

public interface GetService<T, F> {
    T get(F filter);
}

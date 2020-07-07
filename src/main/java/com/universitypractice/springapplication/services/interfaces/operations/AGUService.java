package com.universitypractice.springapplication.services.interfaces.operations;

public interface AGUService<T, U, ID> extends AddService<T>, GetService<T, ID>, UpdateService<U, ID> {

}

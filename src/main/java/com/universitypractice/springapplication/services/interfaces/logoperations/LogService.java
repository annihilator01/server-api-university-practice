package com.universitypractice.springapplication.services.interfaces.logoperations;

import com.universitypractice.springapplication.services.interfaces.baseoperations.AddService;
import com.universitypractice.springapplication.services.interfaces.baseoperations.GetService;

public interface LogService<T, ID> extends AddService<T>, GetService<T, ID> {

}

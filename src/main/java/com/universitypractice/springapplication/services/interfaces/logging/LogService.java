package com.universitypractice.springapplication.services.interfaces.logging;

import com.universitypractice.springapplication.services.interfaces.operations.AddService;
import com.universitypractice.springapplication.services.interfaces.operations.GetService;

public interface LogService<T, ID> extends AddService<T>, GetService<T, ID> {

}

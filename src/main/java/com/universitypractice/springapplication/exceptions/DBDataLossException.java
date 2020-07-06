package com.universitypractice.springapplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DBDataLossException extends RuntimeException {
    public DBDataLossException(String message) {
        super(message);
    }
}

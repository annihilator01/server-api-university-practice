package com.universitypractice.springapplication.exceptions;

import org.springframework.http.HttpStatus;

public class ElementAlreadyExistsException extends CustomRuntimeResponseException {
    public ElementAlreadyExistsException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}

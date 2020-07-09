package com.universitypractice.springapplication.exceptions;

import org.springframework.http.HttpStatus;

public class ElementNotFoundException extends CustomRuntimeResponseException {
    public ElementNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}

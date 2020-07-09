package com.universitypractice.springapplication.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidDataException extends CustomRuntimeResponseException {
    public InvalidDataException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}

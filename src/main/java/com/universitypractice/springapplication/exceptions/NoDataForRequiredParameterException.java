package com.universitypractice.springapplication.exceptions;

import org.springframework.http.HttpStatus;

public class NoDataForRequiredParameterException extends CustomRuntimeResponseException {
    public NoDataForRequiredParameterException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}

package com.universitypractice.springapplication.exceptions;

public class NoDataForRequiredParameterException extends RuntimeException {
    public NoDataForRequiredParameterException(String message) {
        super(message);
    }
}

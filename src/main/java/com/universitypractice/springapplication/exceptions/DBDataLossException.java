package com.universitypractice.springapplication.exceptions;

public class DBDataLossException extends RuntimeException {
    public DBDataLossException(String message) {
        super(message);
    }
}

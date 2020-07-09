package com.universitypractice.springapplication.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomRuntimeResponseException extends RuntimeException {

    @NonNull
    private final HttpStatus httpStatus;

    public CustomRuntimeResponseException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}

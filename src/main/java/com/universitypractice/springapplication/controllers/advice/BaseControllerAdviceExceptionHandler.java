package com.universitypractice.springapplication.controllers.advice;

import com.universitypractice.springapplication.dtos.response.ApiExceptionResponseDTO;
import com.universitypractice.springapplication.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class BaseControllerAdviceExceptionHandler {

    @ExceptionHandler(value = CustomRuntimeResponseException.class)
    public ResponseEntity<ApiExceptionResponseDTO> handleCustomRuntimeResponseException(CustomRuntimeResponseException crre,
                                                                                        HttpServletRequest request) {
        ApiExceptionResponseDTO apiExceptionResponseDTO = new ApiExceptionResponseDTO(
                ZonedDateTime.now(ZoneId.of("GMT")),
                crre.getHttpStatus().value(),
                crre.getHttpStatus().getReasonPhrase(),
                crre.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(apiExceptionResponseDTO, crre.getHttpStatus());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ApiExceptionResponseDTO handleConstraintViolationException(ConstraintViolationException cve, HttpServletRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        String exceptionMessage = new ArrayList<>(cve.getConstraintViolations()).get(0).getMessage();

        ApiExceptionResponseDTO apiExceptionResponseDTO = new ApiExceptionResponseDTO(
                ZonedDateTime.now(ZoneId.of("GMT")),
                badRequest.value(),
                badRequest.getReasonPhrase(),
                exceptionMessage,
                request.getRequestURI()
        );

        return apiExceptionResponseDTO;
    }
}

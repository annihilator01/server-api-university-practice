package com.universitypractice.springapplication.controllers.advice;

import com.universitypractice.springapplication.dtos.response.ApiExceptionResponseDTO;
import com.universitypractice.springapplication.exceptions.ElementAlreadyExistsException;
import com.universitypractice.springapplication.exceptions.ElementNotFoundException;
import com.universitypractice.springapplication.exceptions.InvalidDataException;
import com.universitypractice.springapplication.exceptions.NoDataForRequiredParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class BaseControllerAdviceExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidDataException.class)
    public ApiExceptionResponseDTO handleInvalidDataException(InvalidDataException ide, HttpServletRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiExceptionResponseDTO apiExceptionResponseDTO = new ApiExceptionResponseDTO(
                ZonedDateTime.now(ZoneId.of("GMT")),
                badRequest.value(),
                badRequest.getReasonPhrase(),
                ide.getMessage(),
                request.getRequestURI()
        );

        return apiExceptionResponseDTO;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NoDataForRequiredParameterException.class)
    public ApiExceptionResponseDTO handleNoDataForRequiredParameterException(NoDataForRequiredParameterException ndfrpe,
                                                                                             HttpServletRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiExceptionResponseDTO apiExceptionResponseDTO = new ApiExceptionResponseDTO(
                ZonedDateTime.now(ZoneId.of("GMT")),
                badRequest.value(),
                badRequest.getReasonPhrase(),
                ndfrpe.getMessage(),
                request.getRequestURI()
        );

        return apiExceptionResponseDTO;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ElementNotFoundException.class)
    public ApiExceptionResponseDTO handleElementNotFoundException(ElementNotFoundException enfe, HttpServletRequest request) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;

        ApiExceptionResponseDTO apiExceptionResponseDTO = new ApiExceptionResponseDTO(
                ZonedDateTime.now(ZoneId.of("GMT")),
                notFound.value(),
                notFound.getReasonPhrase(),
                enfe.getMessage(),
                request.getRequestURI()
        );

        return apiExceptionResponseDTO;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = ElementAlreadyExistsException.class)
    public ApiExceptionResponseDTO handleElementAlreadyExistsException(ElementAlreadyExistsException eae, HttpServletRequest request) {
        HttpStatus conflict = HttpStatus.CONFLICT;

        ApiExceptionResponseDTO apiExceptionResponseDTO = new ApiExceptionResponseDTO(
                ZonedDateTime.now(ZoneId.of("GMT")),
                conflict.value(),
                conflict.getReasonPhrase(),
                eae.getMessage(),
                request.getRequestURI()
        );

        return apiExceptionResponseDTO;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ApiExceptionResponseDTO handleConstraintViolationException(ConstraintViolationException cve, HttpServletRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiExceptionResponseDTO apiExceptionResponseDTO = new ApiExceptionResponseDTO(
                ZonedDateTime.now(ZoneId.of("GMT")),
                badRequest.value(),
                badRequest.getReasonPhrase(),
                cve.getMessage(),
                request.getRequestURI()
        );

        return apiExceptionResponseDTO;
    }
}

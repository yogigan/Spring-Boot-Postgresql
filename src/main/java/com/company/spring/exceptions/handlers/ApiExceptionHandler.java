package com.company.spring.exceptions.handlers;

import com.company.spring.exceptions.ApiBadRequestException;
import com.company.spring.exceptions.ApiConflictException;
import com.company.spring.exceptions.ApiInternalErrorException;
import com.company.spring.exceptions.ApiNotFoundException;
import com.company.spring.exceptions.models.ApiResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({ApiBadRequestException.class})
    public ResponseEntity<?> handlerApiBadRequestException(ApiBadRequestException e) {
        return setResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler({ApiNotFoundException.class})
    public ResponseEntity<?> handlerApiDataNotFoundException(ApiNotFoundException e) {
        return setResponse(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler({ApiConflictException.class})
    public ResponseEntity<?> handlerApiConflictException(ApiConflictException e) {
        return setResponse(HttpStatus.CONFLICT, e);
    }

    @ExceptionHandler({InternalError.class})
    public ResponseEntity<?> handlerApiInternalErrorException(ApiInternalErrorException e) {
        return setResponse(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    public ResponseEntity<?> setResponse(HttpStatus httpStatus, RuntimeException e) {
        // 1. create payload exception detail
        ApiResponseException apiResponseException = ApiResponseException.builder()
                .message(e.getMessage())
                .code(httpStatus.value())
                .status(httpStatus.name())
                .timestamp(ZonedDateTime.now())
                .build();

        // 2.  return response entity
        return new ResponseEntity<>(apiResponseException, httpStatus);
    }

}

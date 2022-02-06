package com.company.spring.exceptions;

public class ApiInternalErrorException extends RuntimeException{

    public ApiInternalErrorException(String message) {
        super(message);
    }
}

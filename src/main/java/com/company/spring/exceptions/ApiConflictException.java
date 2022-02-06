package com.company.spring.exceptions;

public class ApiConflictException extends RuntimeException{

    public ApiConflictException(String message) {
        super(message);
    }
}

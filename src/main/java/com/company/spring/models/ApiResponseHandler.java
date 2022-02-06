package com.company.spring.models;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseHandler {

    public static ResponseEntity<?> generateResponse(Object data, String message) {
        HttpStatus httpStatus = HttpStatus.OK;
        ApiResponseData apiResponseData = ApiResponseData.builder()
                .code(httpStatus.value())
                .status(httpStatus.name())
                .message(message)
                .data(data)
                .build();
        return new ResponseEntity<>(apiResponseData, httpStatus);
    }
}

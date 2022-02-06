package com.company.spring.exceptions.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Builder
@AllArgsConstructor
@Getter
public class ApiResponseException {

    private final String message;
    private final Integer code;
    private final String status;
    private final ZonedDateTime timestamp;
}

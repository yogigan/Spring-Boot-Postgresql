package com.company.spring.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Setter
@Getter
public class ApiResponseData {

    private final Integer code;
    private final String status;
    private final String message;
    private final Object data;

}

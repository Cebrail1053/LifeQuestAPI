package com.gabetech.lifequest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {

    private int statusCode;
    private String message;
    private T data;
    private Object metadata;
}

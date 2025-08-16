package com.gabetech.lifequest.common.utils;

import com.gabetech.lifequest.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class ResponseUtil {

    public static <T> ApiResponse<T> success (String message, T data, Object metadata) {
        return new ApiResponse<>(HttpStatus.OK.value(), message, data, metadata);
    }

    public static <T> ApiResponse<T> error (String message, T data) {
        return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, data, null);
    }
}

package com.example.bugle_be.global.error.response;

import com.example.bugle_be.global.error.exception.ErrorProperty;
import org.springframework.http.HttpStatus;

public record ErrorResponse(
    HttpStatus status,
    String message
) {

    public static ErrorResponse of(ErrorProperty errorProperty) {
        return new ErrorResponse(
            errorProperty.getStatus(),
            errorProperty.getMessage()
        );
    }
}

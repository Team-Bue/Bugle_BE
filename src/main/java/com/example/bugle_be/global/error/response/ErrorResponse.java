package com.example.bugle_be.global.error.response;

import com.example.bugle_be.global.error.exception.ErrorProperty;

public record ErrorResponse(
    int status,
    String message
) {

    public static ErrorResponse of(ErrorProperty errorProperty) {
        return new ErrorResponse(
            errorProperty.getStatus().value(),
            errorProperty.getMessage()
        );
    }
}

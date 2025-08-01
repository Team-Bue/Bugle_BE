package com.example.bugle_be.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements ErrorProperty {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),

    EXPIRED_JWT(HttpStatus.UNAUTHORIZED, "Expired JWT"),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "Invalid JWT");

    private final HttpStatus status;
    private final String message;
}

package com.example.bugle_be.infra.s3.exception.error;

import com.example.bugle_be.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum S3ErrorCode implements ErrorProperty {

    OBJECT_KEY_NOT_FOUND(HttpStatus.NOT_FOUND, "Object Key Not Found"),
    INTERNAL_S3_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal S3 Error");

    private final HttpStatus status;
    private final String message;
}

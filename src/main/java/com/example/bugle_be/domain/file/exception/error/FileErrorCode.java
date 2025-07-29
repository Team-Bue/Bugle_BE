package com.example.bugle_be.domain.file.exception.error;

import com.example.bugle_be.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FileErrorCode implements ErrorProperty {

    UNSUPPORTED_FILE_EXTENSION(HttpStatus.BAD_REQUEST, "Unsupported file extension"),
    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "File not found");

    private final HttpStatus status;
    private final String message;
}

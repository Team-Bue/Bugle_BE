package com.example.bugle_be.domain.user.exception.error;

import com.example.bugle_be.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorProperty {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User Not Found");

    private final HttpStatus status;
    private final String message;
}

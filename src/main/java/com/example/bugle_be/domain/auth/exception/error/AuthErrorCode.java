package com.example.bugle_be.domain.auth.exception.error;

import com.example.bugle_be.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements ErrorProperty {

    ALREADY_EMAIL_EXISTS(HttpStatus.CONFLICT, "Already Exists Email"),
    ALREADY_ACCOUNT_ID_EXISTS(HttpStatus.CONFLICT, "Already AccountId Exists");

    private final HttpStatus status;
    private final String message;
}

package com.example.bugle_be.domain.auth.exception.error;

import com.example.bugle_be.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements ErrorProperty {

    // email
    ALREADY_EMAIL_EXISTS(HttpStatus.CONFLICT, "Already exists email"),
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "Email not found"),

    // accountId
    ALREADY_ACCOUNT_ID_EXISTS(HttpStatus.CONFLICT, "Already exists account ID"),
    ACCOUNT_ID_NOT_FOUND(HttpStatus.NOT_FOUND, "Account ID not found"),

    // auth
    PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED, "Password mismatch"),
    LOGIN_IDENTIFIER_NOT_PROVIDED(HttpStatus.BAD_REQUEST, "Login identifier not provided"),

    // refreshToken
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "Refresh token not found"),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid refresh token");

    private final HttpStatus status;
    private final String message;
}

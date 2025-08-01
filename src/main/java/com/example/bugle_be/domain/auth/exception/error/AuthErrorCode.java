package com.example.bugle_be.domain.auth.exception.error;

import com.example.bugle_be.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements ErrorProperty {

    // email
    ALREADY_EMAIL_EXISTS(HttpStatus.CONFLICT, "Already Exists Email"),
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "Email Not Found"),

    // accountId
    ALREADY_ACCOUNT_ID_EXISTS(HttpStatus.CONFLICT, "Already AccountId Exists"),
    ACCOUNT_ID_NOT_FOUND(HttpStatus.CONFLICT, "Account Id Not Found"),

    // auth
    PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED, "Password Mismatch"),
    LOGIN_IDENTIFIER_NOT_PROVIDED(HttpStatus.BAD_REQUEST, "Login Identifier Not Provided");

    private final HttpStatus status;
    private final String message;
}

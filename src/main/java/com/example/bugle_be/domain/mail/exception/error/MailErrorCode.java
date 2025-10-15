package com.example.bugle_be.domain.mail.exception.error;

import com.example.bugle_be.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MailErrorCode implements ErrorProperty {

    // code
    CODE_MISMATCH(HttpStatus.BAD_REQUEST, "Code mismatch"),

    // mail
    MAIL_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Mail send failed"),

    // hash
    HASHING_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Hashing failed"),

    // token
    TOKEN_MISMATCH(HttpStatus.UNAUTHORIZED, "Token mismatch");

    private final HttpStatus status;
    private final String message;
}

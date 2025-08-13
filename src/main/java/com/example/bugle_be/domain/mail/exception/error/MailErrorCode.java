package com.example.bugle_be.domain.mail.exception.error;

import com.example.bugle_be.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MailErrorCode implements ErrorProperty {

    CODE_MISMATCH(HttpStatus.BAD_REQUEST, "Code Mismatch"),

    MAIL_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Mail Send failed");

    private final HttpStatus status;
    private final String message;
}

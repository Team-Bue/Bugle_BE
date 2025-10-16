package com.example.bugle_be.domain.report.exception.error;

import com.example.bugle_be.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReportErrorCode implements ErrorProperty {

    ALREADY_REPORTED(HttpStatus.BAD_REQUEST, "Already reported"),
    INVALID_REPORT_TYPE(HttpStatus.BAD_REQUEST, "Invalid report type");

    private final HttpStatus status;
    private final String message;
}

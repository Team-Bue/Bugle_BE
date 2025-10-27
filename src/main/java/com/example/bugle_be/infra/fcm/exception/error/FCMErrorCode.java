package com.example.bugle_be.infra.fcm.exception.error;

import com.example.bugle_be.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FCMErrorCode implements ErrorProperty {

    DEVICE_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "Device token not found"),
    FCM_INITIALIZATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "FCM initialization failed");

    private final HttpStatus status;
    private final String message;
}

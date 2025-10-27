package com.example.bugle_be.infra.fcm.exception;

import com.example.bugle_be.global.error.exception.BugleException;
import com.example.bugle_be.infra.fcm.exception.error.FCMErrorCode;

public class FCMInitializationFailed extends BugleException {

    public static final BugleException EXCEPTION = new FCMInitializationFailed();

    private FCMInitializationFailed() {
        super(FCMErrorCode.FCM_INITIALIZATION_FAILED);
    }
}

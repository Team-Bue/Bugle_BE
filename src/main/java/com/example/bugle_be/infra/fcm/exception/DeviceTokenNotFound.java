package com.example.bugle_be.infra.fcm.exception;

import com.example.bugle_be.global.error.exception.BugleException;
import com.example.bugle_be.infra.fcm.exception.error.FCMErrorCode;

public class DeviceTokenNotFound extends BugleException {

    public static final BugleException EXCEPTION = new DeviceTokenNotFound();

    public DeviceTokenNotFound() {
        super(FCMErrorCode.DEVICE_TOKEN_NOT_FOUND);
    }
}

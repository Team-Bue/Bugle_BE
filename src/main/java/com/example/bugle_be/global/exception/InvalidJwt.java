package com.example.bugle_be.global.exception;

import com.example.bugle_be.global.error.exception.BugleException;
import com.example.bugle_be.global.error.exception.GlobalErrorCode;

public class InvalidJwt extends BugleException {

    public static final BugleException EXCEPTION = new InvalidJwt();

    private InvalidJwt() {
        super(GlobalErrorCode.INVALID_JWT);
    }
}

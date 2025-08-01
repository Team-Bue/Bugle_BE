package com.example.bugle_be.global.exception;

import com.example.bugle_be.global.error.exception.BugleException;
import com.example.bugle_be.global.error.exception.GlobalErrorCode;

public class ExpiredJwt extends BugleException {

    public static final BugleException EXCEPTION = new ExpiredJwt();

    private ExpiredJwt() {
        super(GlobalErrorCode.EXPIRED_JWT);
    }
}

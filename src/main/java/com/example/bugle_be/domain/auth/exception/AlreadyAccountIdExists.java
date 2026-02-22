package com.example.bugle_be.domain.auth.exception;

import com.example.bugle_be.domain.auth.exception.error.AuthErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class AlreadyAccountIdExists extends BugleException {

    public static final BugleException EXCEPTION = new AlreadyAccountIdExists();

    private AlreadyAccountIdExists() {
        super(AuthErrorCode.ALREADY_ACCOUNT_ID_EXISTS);
    }
}

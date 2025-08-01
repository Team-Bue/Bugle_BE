package com.example.bugle_be.domain.auth.exception;

import com.example.bugle_be.domain.auth.exception.error.AuthErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class AlreadyEmailExists extends BugleException {

    public static final BugleException EXCEPTION = new AlreadyEmailExists();

    private AlreadyEmailExists() {
        super(AuthErrorCode.ALREADY_EMAIL_EXISTS);
    }
}

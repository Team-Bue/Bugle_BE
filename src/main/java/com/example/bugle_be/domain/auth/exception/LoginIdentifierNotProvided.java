package com.example.bugle_be.domain.auth.exception;

import com.example.bugle_be.domain.auth.exception.error.AuthErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class LoginIdentifierNotProvided extends BugleException {

    public static final BugleException EXCEPTION = new LoginIdentifierNotProvided();

    private LoginIdentifierNotProvided() {
        super(AuthErrorCode.LOGIN_IDENTIFIER_NOT_PROVIDED);
    }
}

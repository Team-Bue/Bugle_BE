package com.example.bugle_be.domain.auth.exception;

import com.example.bugle_be.domain.auth.exception.error.AuthErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class InvalidRefreshToken extends BugleException {

    public static final BugleException EXCEPTION = new InvalidRefreshToken();

    private InvalidRefreshToken() {
        super(AuthErrorCode.INVALID_REFRESH_TOKEN);
    }
}

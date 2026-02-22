package com.example.bugle_be.domain.auth.exception;

import com.example.bugle_be.domain.auth.exception.error.AuthErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class RefreshTokenNotFound extends BugleException {

    public static final BugleException EXCEPTION = new RefreshTokenNotFound();

    private RefreshTokenNotFound() {
        super(AuthErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}

package com.example.bugle_be.domain.auth.exception;

import com.example.bugle_be.domain.auth.exception.error.AuthErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class EmailNotFound extends BugleException {

    public static final BugleException EXCEPTION = new EmailNotFound();

    private EmailNotFound() {
        super(AuthErrorCode.EMAIL_NOT_FOUND);
    }
}

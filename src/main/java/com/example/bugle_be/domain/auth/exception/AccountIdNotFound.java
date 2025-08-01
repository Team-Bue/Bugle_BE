package com.example.bugle_be.domain.auth.exception;

import com.example.bugle_be.domain.auth.exception.error.AuthErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class AccountIdNotFound extends BugleException {

    public static final BugleException EXCEPTION = new AccountIdNotFound();

    private AccountIdNotFound() {
        super(AuthErrorCode.ACCOUNT_ID_NOT_FOUND);
    }
}

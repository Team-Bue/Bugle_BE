package com.example.bugle_be.domain.user.exception;

import com.example.bugle_be.domain.user.exception.error.UserErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class AccountIdAlreadyExists extends BugleException {

    public static final BugleException EXCEPTION = new AccountIdAlreadyExists();

    private AccountIdAlreadyExists() {
        super(UserErrorCode.ACCOUNT_ID_ALREADY_EXISTS);
    }
}

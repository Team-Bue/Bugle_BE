package com.example.bugle_be.domain.user.exception;

import com.example.bugle_be.domain.user.exception.error.UserErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class UserNotFound extends BugleException {

    public static final BugleException EXCEPTION = new UserNotFound();

    private UserNotFound() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}

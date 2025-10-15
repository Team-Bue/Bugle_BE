package com.example.bugle_be.domain.follow.exception;

import com.example.bugle_be.domain.follow.exception.error.FollowErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class CannotFollowYourself extends BugleException {

    public static final BugleException EXCEPTION = new CannotFollowYourself();

    private CannotFollowYourself() {
        super(FollowErrorCode.CANNOT_FOLLOW_YOURSELF);
    }
}

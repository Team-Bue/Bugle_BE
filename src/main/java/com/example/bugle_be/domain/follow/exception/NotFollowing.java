package com.example.bugle_be.domain.follow.exception;

import com.example.bugle_be.domain.follow.exception.error.FollowErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class NotFollowing extends BugleException {

    public static final BugleException EXCEPTION = new NotFollowing();

    private NotFollowing() {
        super(FollowErrorCode.NOT_FOLLOWING);
    }
}

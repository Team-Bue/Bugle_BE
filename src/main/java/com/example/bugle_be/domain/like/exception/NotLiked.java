package com.example.bugle_be.domain.like.exception;

import com.example.bugle_be.domain.like.exception.error.LikeErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class NotLiked extends BugleException {

    public static final BugleException EXCEPTION = new NotLiked();

    private NotLiked() {
        super(LikeErrorCode.NOT_LIKED);
    }
}

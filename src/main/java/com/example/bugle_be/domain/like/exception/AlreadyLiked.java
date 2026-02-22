package com.example.bugle_be.domain.like.exception;

import com.example.bugle_be.domain.like.exception.error.LikeErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class AlreadyLiked extends BugleException {

    public static final BugleException EXCEPTION = new AlreadyLiked();

    private AlreadyLiked() {
        super(LikeErrorCode.ALREADY_LIKED);
    }
}

package com.example.bugle_be.domain.post.exception;

import com.example.bugle_be.domain.post.exception.error.PostErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class CannotDeletePost extends BugleException {

    public static final BugleException EXCEPTION = new CannotDeletePost();

    private CannotDeletePost() {
        super(PostErrorCode.CANNOT_DELETE_POST);
    }
}

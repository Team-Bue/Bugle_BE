package com.example.bugle_be.domain.post.exception;

import com.example.bugle_be.domain.post.exception.error.PostErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class CannotUpdatePost extends BugleException {

    public static final BugleException EXCEPTION = new CannotUpdatePost();

    private CannotUpdatePost() {
        super(PostErrorCode.CANNOT_UPDATE_POST);
    }
}

package com.example.bugle_be.domain.post.exception;

import com.example.bugle_be.domain.post.exception.error.PostErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class PostNotFound extends BugleException {

    public static final BugleException EXCEPTION = new PostNotFound();

    private PostNotFound() {
        super(PostErrorCode.POST_NOT_FOUND);
    }
}

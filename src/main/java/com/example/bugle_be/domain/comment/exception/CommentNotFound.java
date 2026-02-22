package com.example.bugle_be.domain.comment.exception;

import com.example.bugle_be.domain.comment.exception.error.CommentErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class CommentNotFound extends BugleException {

    public static final BugleException EXCEPTION = new CommentNotFound();

    private CommentNotFound() {
        super(CommentErrorCode.COMMENT_NOT_FOUND);
    }
}

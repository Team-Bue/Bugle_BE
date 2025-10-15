package com.example.bugle_be.domain.comment.exception;

import com.example.bugle_be.domain.comment.exception.error.CommentErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class CannotDeleteComment extends BugleException {

    public static final BugleException EXCEPTION = new CannotDeleteComment();

    private CannotDeleteComment() {
        super(CommentErrorCode.CANNOT_DELETE_COMMENT);
    }
}

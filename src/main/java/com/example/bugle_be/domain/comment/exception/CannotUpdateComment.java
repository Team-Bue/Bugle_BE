package com.example.bugle_be.domain.comment.exception;

import com.example.bugle_be.domain.comment.exception.error.CommentErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class CannotUpdateComment extends BugleException {

    public static final BugleException EXCEPTION = new CannotUpdateComment();

    private CannotUpdateComment() {
        super(CommentErrorCode.CANNOT_UPDATE_COMMENT);
    }
}

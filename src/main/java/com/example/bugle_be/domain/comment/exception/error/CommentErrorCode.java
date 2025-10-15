package com.example.bugle_be.domain.comment.exception.error;

import com.example.bugle_be.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommentErrorCode implements ErrorProperty {

    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Comment not found"),
    CANNOT_UPDATE_COMMENT(HttpStatus.FORBIDDEN, "Can't update comment"),
    CANNOT_DELETE_COMMENT(HttpStatus.FORBIDDEN, "Can't delete comment");

    private final HttpStatus status;
    private final String message;
}

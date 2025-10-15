package com.example.bugle_be.domain.post.exception.error;

import com.example.bugle_be.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PostErrorCode implements ErrorProperty {

    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "Post Not Found");

    private final HttpStatus status;
    private final String message;
}

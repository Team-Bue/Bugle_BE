package com.example.bugle_be.domain.like.exception.error;

import com.example.bugle_be.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum LikeErrorCode implements ErrorProperty {

    ALREADY_LIKED(HttpStatus.BAD_REQUEST, "Already like this post"),
    NOT_LIKED(HttpStatus.BAD_REQUEST, "Not like this post"),;

    private final HttpStatus status;
    private final String message;
}

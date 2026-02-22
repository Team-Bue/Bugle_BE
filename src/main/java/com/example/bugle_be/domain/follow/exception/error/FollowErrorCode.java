package com.example.bugle_be.domain.follow.exception.error;

import com.example.bugle_be.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FollowErrorCode implements ErrorProperty {

    CANNOT_FOLLOW_YOURSELF(HttpStatus.BAD_REQUEST, "Cannot follow yourself"),
    ALREADY_FOLLOWED(HttpStatus.BAD_REQUEST, "Already following this user"),
    NOT_FOLLOWING(HttpStatus.BAD_REQUEST, "Not following this user");

    private final HttpStatus status;
    private final String message;
}

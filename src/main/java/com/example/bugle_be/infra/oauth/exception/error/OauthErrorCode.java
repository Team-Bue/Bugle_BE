package com.example.bugle_be.infra.oauth.exception.error;

import com.example.bugle_be.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OauthErrorCode implements ErrorProperty {

    OAUTH_EMAIL_NOT_FOUND(HttpStatus.UNAUTHORIZED, "Oauth email not found"),
    OAUTH_ACCOUNT_ID_NOT_FOUND(HttpStatus.UNAUTHORIZED, "Oauth accountId not found"),

    // kakao
    KAKAO_ACCOUNT_NOT_FOUND(HttpStatus.UNAUTHORIZED, "Kakao account not found"),
    KAKAO_PROFILE_NOT_FOUND(HttpStatus.UNAUTHORIZED, "Kakao profile not found"),
    UNSUPPORTED_PROVIDER(HttpStatus.BAD_REQUEST, "Unsupported provider for authentication");

    private final HttpStatus status;
    private final String message;
}

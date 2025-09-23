package com.example.bugle_be.infra.oauth.provider;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Oauth2Provider {

    KAKAO("kakao"),
    UNKNOWN("unknown");

    private final String registrationId;
}

package com.example.bugle_be.infra.oauth.info;

import com.example.bugle_be.infra.oauth.provider.Oauth2Provider;

public interface Oauth2UserInfo {

    String getEmail();

    String getAccountId();

    String getProfileImageUrl();

    Oauth2Provider getOauth2Provider();
}

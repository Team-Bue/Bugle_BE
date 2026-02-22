package com.example.bugle_be.infra.oauth.info.kakao;

import com.example.bugle_be.infra.oauth.exception.KakaoAccountNotFound;
import com.example.bugle_be.infra.oauth.exception.KakaoProfileNotFound;
import com.example.bugle_be.infra.oauth.exception.OauthAccountIdNotFound;
import com.example.bugle_be.infra.oauth.exception.OauthEmailNotFound;
import com.example.bugle_be.infra.oauth.info.Oauth2UserInfo;
import com.example.bugle_be.infra.oauth.provider.Oauth2Provider;

import java.util.Map;

public class KakaoOauth2UserInfo implements Oauth2UserInfo {

    private final String email;
    private final String accountId;

    public KakaoOauth2UserInfo(Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        if (kakaoAccount == null) {
            throw KakaoAccountNotFound.EXCEPTION;
        }

        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");
        if (kakaoProfile == null) {
            throw KakaoProfileNotFound.EXCEPTION;
        }

        this.email = (String) kakaoAccount.get("email");
        if (email == null) {
            throw OauthEmailNotFound.EXCEPTION;
        }

        this.accountId = (String) kakaoProfile.get("nickname");
        if (accountId == null) {
            throw OauthAccountIdNotFound.EXCEPTION;
        }
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getAccountId() {
        return accountId;
    }

    @Override
    public Oauth2Provider getOauth2Provider() {
        return Oauth2Provider.KAKAO;
    }
}

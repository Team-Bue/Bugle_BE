package com.example.bugle_be.infra.oauth.factory;

import com.example.bugle_be.infra.oauth.exception.UnsupportedProvider;
import com.example.bugle_be.infra.oauth.info.Oauth2UserInfo;
import com.example.bugle_be.infra.oauth.info.kakao.KakaoOauth2UserInfo;
import com.example.bugle_be.infra.oauth.provider.Oauth2Provider;

import java.util.Map;

public class Oauth2UserInfoFactory {

    public static Oauth2UserInfo getOauth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equals(Oauth2Provider.KAKAO.getRegistrationId())) {
            return new KakaoOauth2UserInfo(attributes);
        } else {
            throw UnsupportedProvider.EXCEPTION;
        }
    }
}

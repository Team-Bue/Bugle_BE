package com.example.bugle_be.infra.oauth.exception;

import com.example.bugle_be.global.error.exception.BugleException;
import com.example.bugle_be.infra.oauth.exception.error.OauthErrorCode;

public class KakaoProfileNotFound extends BugleException {

    public static final BugleException EXCEPTION = new KakaoProfileNotFound();

    private KakaoProfileNotFound() {
        super(OauthErrorCode.KAKAO_PROFILE_NOT_FOUND);
    }
}

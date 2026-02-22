package com.example.bugle_be.infra.oauth.exception;

import com.example.bugle_be.global.error.exception.BugleException;
import com.example.bugle_be.infra.oauth.exception.error.OauthErrorCode;

public class KakaoAccountNotFound extends BugleException {

    public static final BugleException EXCEPTION = new KakaoAccountNotFound();

    private KakaoAccountNotFound() {
        super(OauthErrorCode.KAKAO_ACCOUNT_NOT_FOUND);
    }
}

package com.example.bugle_be.infra.oauth.exception;

import com.example.bugle_be.global.error.exception.BugleException;
import com.example.bugle_be.infra.oauth.exception.error.OauthErrorCode;

public class UnsupportedProvider extends BugleException {

    public static final BugleException EXCEPTION = new UnsupportedProvider();

    private UnsupportedProvider() {
        super(OauthErrorCode.UNSUPPORTED_PROVIDER);
    }
}

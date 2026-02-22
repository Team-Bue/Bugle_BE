package com.example.bugle_be.infra.oauth.exception;

import com.example.bugle_be.global.error.exception.BugleException;
import com.example.bugle_be.infra.oauth.exception.error.OauthErrorCode;

public class OauthEmailNotFound extends BugleException {

    public static final BugleException EXCEPTION = new OauthEmailNotFound();

    private OauthEmailNotFound() {
        super(OauthErrorCode.OAUTH_EMAIL_NOT_FOUND);
    }
}

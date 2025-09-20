package com.example.bugle_be.infra.oauth.exception;

import com.example.bugle_be.global.error.exception.BugleException;
import com.example.bugle_be.infra.oauth.exception.error.OauthErrorCode;

public class OauthAccountIdNotFound extends BugleException {

    public static final BugleException EXCEPTION = new OauthAccountIdNotFound();

    private OauthAccountIdNotFound() {
        super(OauthErrorCode.OAUTH_ACCOUNT_ID_NOT_FOUND);
    }
}

package com.example.bugle_be.domain.mail.exception;

import com.example.bugle_be.domain.mail.exception.error.MailErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class TokenMisMatch extends BugleException {

    public static final BugleException EXCEPTION = new TokenMisMatch();

    private TokenMisMatch() {
        super(MailErrorCode.CODE_MISMATCH);
    }
}

package com.example.bugle_be.domain.mail.exception;

import com.example.bugle_be.domain.mail.exception.error.MailErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class HashingFailed extends BugleException {

    public static final BugleException EXCEPTION = new HashingFailed();

    private HashingFailed() {
        super(MailErrorCode.HASHING_FAILED);
    }
}

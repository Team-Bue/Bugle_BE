package com.example.bugle_be.domain.mail.exception;

import com.example.bugle_be.domain.mail.exception.error.MailErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class MailSendFailed extends BugleException {

    public static final BugleException EXCEPTION = new MailSendFailed();

    private MailSendFailed() {
        super(MailErrorCode.MAIL_SEND_FAILED);
    }
}

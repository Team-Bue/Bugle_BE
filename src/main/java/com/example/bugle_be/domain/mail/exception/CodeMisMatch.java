package com.example.bugle_be.domain.mail.exception;

import com.example.bugle_be.domain.mail.exception.error.MailErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class CodeMisMatch extends BugleException {

    public static final BugleException EXCEPTION = new CodeMisMatch();

    private CodeMisMatch() {
        super(MailErrorCode.CODE_MISMATCH);
    }
}

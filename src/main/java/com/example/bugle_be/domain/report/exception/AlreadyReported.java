package com.example.bugle_be.domain.report.exception;

import com.example.bugle_be.domain.report.exception.error.ReportErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class AlreadyReported extends BugleException {

    public static final BugleException EXCEPTION = new AlreadyReported();

    private AlreadyReported() {
        super(ReportErrorCode.ALREADY_REPORTED);
    }
}

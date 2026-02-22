package com.example.bugle_be.domain.report.exception;

import com.example.bugle_be.domain.report.exception.error.ReportErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class InvalidReportType extends BugleException {

    public static final BugleException EXCEPTION = new InvalidReportType();

    private InvalidReportType() {
        super(ReportErrorCode.INVALID_REPORT_TYPE);
    }
}

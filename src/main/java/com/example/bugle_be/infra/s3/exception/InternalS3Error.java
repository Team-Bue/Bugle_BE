package com.example.bugle_be.infra.s3.exception;

import com.example.bugle_be.global.error.exception.BugleException;
import com.example.bugle_be.infra.s3.exception.error.S3ErrorCode;

public class InternalS3Error extends BugleException {

    public static final BugleException EXCEPTION = new InternalS3Error();

    private InternalS3Error() {
        super(S3ErrorCode.INTERNAL_S3_ERROR);
    }
}

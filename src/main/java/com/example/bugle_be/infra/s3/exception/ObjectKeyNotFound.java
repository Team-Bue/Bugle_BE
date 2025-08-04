package com.example.bugle_be.infra.s3.exception;

import com.example.bugle_be.global.error.exception.BugleException;
import com.example.bugle_be.infra.s3.exception.error.S3ErrorCode;

public class ObjectKeyNotFound extends BugleException {

    public static final BugleException EXCEPTION = new ObjectKeyNotFound();

    private ObjectKeyNotFound() {
        super(S3ErrorCode.OBJECT_KEY_NOT_FOUND);
    }
}

package com.example.bugle_be.global.exception;

import com.example.bugle_be.global.error.exception.BugleException;
import com.example.bugle_be.global.error.exception.GlobalErrorCode;

public class InvalidPaginationParameter extends BugleException {

    public static final BugleException EXCEPTION = new InvalidPaginationParameter();

    private InvalidPaginationParameter() {
        super(GlobalErrorCode.INVALID_PAGINATION_PARAMETER);
    }
}

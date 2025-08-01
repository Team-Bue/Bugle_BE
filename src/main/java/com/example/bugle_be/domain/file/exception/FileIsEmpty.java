package com.example.bugle_be.domain.file.exception;

import com.example.bugle_be.domain.file.exception.error.FileErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class FileIsEmpty extends BugleException {

    public static final BugleException EXCEPTION = new FileIsEmpty();

    private FileIsEmpty() {
        super(FileErrorCode.FILE_IS_EMPTY);
    }
}
package com.example.bugle_be.domain.file.exception;

import com.example.bugle_be.domain.file.exception.error.FileErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class UnsupportedFileExtension extends BugleException {

    public static final BugleException EXCEPTION = new UnsupportedFileExtension();

    private UnsupportedFileExtension() {
        super(FileErrorCode.UNSUPPORTED_FILE_EXTENSION);
    }
}
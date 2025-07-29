package com.example.bugle_be.domain.file.exception;

import com.example.bugle_be.domain.file.exception.error.FileErrorCode;
import com.example.bugle_be.global.error.exception.BugleException;

public class FileNotFound extends BugleException {

    public static final BugleException EXCEPTION = new FileNotFound();

    private FileNotFound() {
        super(FileErrorCode.FILE_NOT_FOUND);
    }

}
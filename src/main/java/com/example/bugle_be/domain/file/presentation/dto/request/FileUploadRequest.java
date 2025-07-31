package com.example.bugle_be.domain.file.presentation.dto.request;

import com.example.bugle_be.domain.file.type.FileType;

public record FileUploadRequest(
    FileType fileType,
    String fileName
) {
}
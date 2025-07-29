package com.example.bugle_be.domain.file.presentation.dto.response;

import java.util.List;

public record FileUploadUrlResponse(
    List<FileUploadUrl> responses
) {
    public record FileUploadUrl(
        String fileName,
        String presignedUrl
    ) {}
}
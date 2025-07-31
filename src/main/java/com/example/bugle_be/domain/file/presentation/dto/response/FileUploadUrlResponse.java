package com.example.bugle_be.domain.file.presentation.dto.response;

public record FileUploadUrlResponse(
    String objectKey,
    String presignedUrl
) {
}
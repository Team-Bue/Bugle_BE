package com.example.bugle_be.domain.file.presentation.dto.request;

import com.example.bugle_be.domain.file.type.FileType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FileUploadRequest(
    @NotNull
    FileType fileType,

    @NotBlank
    String fileName
) {
}

package com.example.bugle_be.domain.file.presentation.dto.request;

import com.example.bugle_be.domain.file.type.FileType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record FileUploadRequest(
    @NotEmpty
    @Size(max = 10, message = "파일은 최대 10개까지 업로드할 수 있습니다.")
    List<UploadFile> files
) {
    public record UploadFile(
        FileType fileType,
        String fileName
    ) {}
}
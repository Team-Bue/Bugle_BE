package com.example.bugle_be.domain.file.service;

import com.example.bugle_be.domain.file.exception.FileNotFound;
import com.example.bugle_be.domain.file.exception.UnsupportedFileExtension;
import com.example.bugle_be.domain.file.presentation.dto.request.FileUploadRequest;
import com.example.bugle_be.domain.file.presentation.dto.response.FileUploadUrlResponse;
import com.example.bugle_be.domain.file.type.FileType;
import com.example.bugle_be.infra.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final S3Service s3Service;
    private static final Duration DEFAULT_DURATION = Duration.ofMinutes(2);

    public FileUploadUrlResponse execute(FileUploadRequest request) {
        List<FileUploadUrlResponse.FileUploadUrl> urls = request.files().stream()
            .map(file -> {
                validateFileName(file.fileName());
                validateExtension(file.fileName(), file.fileType());

                String fileName = file.fileName();
                String url = s3Service.createUploadPresignedUrl(fileName, DEFAULT_DURATION, file.fileType().getPath()).toString();

                return new FileUploadUrlResponse.FileUploadUrl(fileName, url);
            })
            .toList();

        return new FileUploadUrlResponse(urls);
    }

    private void validateFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw FileNotFound.EXCEPTION;
        }
    }

    private void validateExtension(String fileName, FileType fileType) {
        if (!fileType.isAllowedExtension(fileName)) {
            throw UnsupportedFileExtension.EXCEPTION;
        }
    }

}
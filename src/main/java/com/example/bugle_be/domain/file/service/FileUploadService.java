package com.example.bugle_be.domain.file.service;

import com.example.bugle_be.domain.file.exception.FileIsEmpty;
import com.example.bugle_be.domain.file.exception.UnsupportedFileExtension;
import com.example.bugle_be.domain.file.presentation.dto.request.FileUploadRequest;
import com.example.bugle_be.domain.file.presentation.dto.response.FileUploadUrlResponse;
import com.example.bugle_be.domain.file.type.FileType;
import com.example.bugle_be.infra.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final S3Service s3Service;
    private static final Duration DEFAULT_DURATION = Duration.ofMinutes(2);

    public FileUploadUrlResponse execute(FileUploadRequest request) {
        validateFileName(request.fileName());
        validateExtension(request.fileName(), request.fileType());

        String objectKey = generateObjectKey(request.fileType(), request.fileName());
        String url = s3Service.createUploadPresignedUrl(objectKey, DEFAULT_DURATION).toString();

        return new FileUploadUrlResponse(objectKey, url);
    }

    private void validateFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw FileIsEmpty.EXCEPTION;
        }
    }

    private void validateExtension(String fileName, FileType fileType) {
        if (!fileType.isAllowedExtension(fileName)) {
            throw UnsupportedFileExtension.EXCEPTION;
        }
    }

    private String generateObjectKey(FileType fileType, String fileName) {
        return fileType.getPath().toLowerCase() + "/" + UUID.randomUUID() + "/" + fileName;
    }
}

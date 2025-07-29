package com.example.bugle_be.domain.file.service;

import com.example.bugle_be.domain.file.presentation.dto.request.FileUploadRequest;
import com.example.bugle_be.domain.file.presentation.dto.response.FileUploadUrlResponse;
import com.example.bugle_be.infra.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final S3Service s3Service;

    public FileUploadUrlResponse execute(FileUploadRequest request) {
        Duration duration = Duration.ofMinutes(2);

        List<FileUploadUrlResponse.FileUploadUrl> urls = request.files().stream()
            .map(file -> {
                String fileName = file.fileName();
                String url = s3Service.createUploadPresignedUrl(fileName, duration, file.fileType().getPath()).toString();

                return new FileUploadUrlResponse.FileUploadUrl(fileName, url);
            })
            .toList();

        return new FileUploadUrlResponse(urls);
    }

}
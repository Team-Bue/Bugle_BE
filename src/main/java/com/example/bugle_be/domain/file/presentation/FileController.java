package com.example.bugle_be.domain.file.presentation;

import com.example.bugle_be.domain.file.presentation.dto.request.FileUploadRequest;
import com.example.bugle_be.domain.file.presentation.dto.response.FileUploadUrlResponse;
import com.example.bugle_be.domain.file.service.FileUploadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileUploadService fileUploadService;

    @PostMapping("/pre-signed")
    @ResponseStatus(HttpStatus.CREATED)
    public FileUploadUrlResponse createPresignedUrl(@RequestBody @Valid FileUploadRequest request) {
        return fileUploadService.execute(request);
    }
}

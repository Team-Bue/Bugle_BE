package com.example.bugle_be.infra.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.URL;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Presigner s3Presigner;
    private final S3Properties s3Properties;

    public URL createUploadPresignedUrl(String objectKey, Duration expiration) {
        PutObjectRequest request = PutObjectRequest.builder()
            .bucket(s3Properties.bucket())
            .key(objectKey)
            .build();

        PutObjectPresignRequest presignedRequest = PutObjectPresignRequest.builder()
            .putObjectRequest(request)
            .signatureDuration(expiration)
            .build();

        return s3Presigner.presignPutObject(presignedRequest).url();
    }
}

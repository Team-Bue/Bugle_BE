package com.example.bugle_be.infra.s3.service;

import com.example.bugle_be.infra.s3.exception.InternalS3Error;
import com.example.bugle_be.infra.s3.exception.ObjectKeyNotFound;
import com.example.bugle_be.infra.s3.properties.S3Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;
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

    private void validateObjectExists(String objectKey) {
        try {
            s3Client.headObject(
                req -> req
                    .bucket(s3Properties.bucket())
                    .key(objectKey)
            );
        } catch (NoSuchKeyException e) {
            throw ObjectKeyNotFound.EXCEPTION;
        } catch (S3Exception e) {
            throw InternalS3Error.EXCEPTION;
        }
    }

    private String encodeObjectKey(String objectKey) {
        return Arrays.stream(objectKey.split("/"))
            .map(part -> URLEncoder.encode(part, StandardCharsets.UTF_8))
            .collect(Collectors.joining("/"));
    }

    public String generateUrl(String objectKey) {
        validateObjectExists(objectKey);
        String encodedKey = encodeObjectKey(objectKey);
        return "https://" + s3Properties.bucket() + ".s3." + s3Properties.region() + ".amazonaws.com/" + encodedKey;
    }
}

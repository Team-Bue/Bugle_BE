package com.example.bugle_be.infra.s3.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cloud.aws")
public record S3Properties(
    String bucket,
    String accessKey,
    String secretKey,
    String region
) {
}

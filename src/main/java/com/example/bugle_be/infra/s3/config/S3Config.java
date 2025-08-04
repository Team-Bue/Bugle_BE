package com.example.bugle_be.infra.s3.config;

import com.example.bugle_be.infra.s3.properties.S3Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
@RequiredArgsConstructor
public class S3Config {

    private final S3Properties s3Properties;

    @Bean
    public AwsBasicCredentials awsBasicCredentials() {
        return AwsBasicCredentials.create(
            s3Properties.accessKey(),
            s3Properties.secretKey()
        );
    }

    @Bean
    public S3Presigner s3Presigner(AwsBasicCredentials awsBasicCredentials) {
        return S3Presigner.builder()
            .region(Region.of(s3Properties.region()))
            .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
            .build();
    }

    @Bean
    public S3Client s3Client(AwsBasicCredentials awsBasicCredentials) {
        return S3Client.builder()
            .region(Region.of(s3Properties.region()))
            .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
            .build();
    }
}

package com.wizeline.demoiberia.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

@Configuration
public class RestConfiguration {

    @Value("${r2.accessKey}")
    private String r2AccessKey;

    @Value("${r2.secret.accessKey}")
    private String r2SecretAccessKey;

    @Value("${freepik.key}")
    private String apiKey;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public S3Client s3Client() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(r2AccessKey, r2SecretAccessKey);

        Region eeur = Region.US_EAST_1;
        return S3Client.builder()
                .region(eeur)
                .endpointOverride(URI.create("https://d60eadd7d311f262bd26d6e8ebfaf9b3.eu.r2.cloudflarestorage.com"))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .serviceConfiguration(S3Configuration.builder().pathStyleAccessEnabled(true).build())
                .build();
    }
}

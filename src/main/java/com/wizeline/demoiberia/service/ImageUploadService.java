package com.wizeline.demoiberia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Paths;

@Service
public class ImageUploadService {

    @Autowired
    private S3Client s3Client;

    private final String bucketName = "imagenes";

    private static final String R2_BUCKET = "https://pub-f92dc6d6f6cc435994c0993e60b23530.r2.dev/";

    public String uploadImage(byte [] imageData, final String fileName) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromBytes(imageData));

        System.out.println(response);

        return R2_BUCKET + fileName;
    }

}

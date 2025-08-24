package com.hmsapp.service;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class BucketService {

    private final AmazonS3 amazonS3;

    public BucketService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String uploadFile(MultipartFile file, String bucketName) {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        try {
            amazonS3.putObject(bucketName, fileName, file.getInputStream(), null);
            return amazonS3.getUrl(bucketName, fileName).toString();
        } catch (IOException e) {
            throw new RuntimeException("Upload failed: " + e.getMessage());
        }
    }
}
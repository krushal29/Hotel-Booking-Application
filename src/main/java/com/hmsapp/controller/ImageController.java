package com.hmsapp.controller;

import com.hmsapp.service.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private final BucketService bucketService;

    public ImageController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @PostMapping("/upload/file/{bucketName}")
    public ResponseEntity<?> uploadFileToS3(
            @RequestParam("file") MultipartFile file,
            @PathVariable String bucketName) {

        String imageUrl = bucketService.uploadFile(file, bucketName);
        return new ResponseEntity<>(imageUrl, HttpStatus.OK);
    }
}
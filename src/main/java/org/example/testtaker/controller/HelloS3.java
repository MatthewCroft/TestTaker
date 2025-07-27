package org.example.testtaker.controller;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

import java.util.List;

public class HelloS3 {
    public static void main(String... args) {
        S3Client s3 = S3Client.builder()
                .region(Region.US_EAST_1)
                .build();

        try {
            ListBucketsResponse response = s3.listBuckets();
            List<Bucket> bucketList = response.buckets();
            for (Bucket bucket : bucketList) {
               System.out.println(bucket.name());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

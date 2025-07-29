package org.example.testtaker.service;

import org.example.testtaker.dto.QuestionMediaUploadRequest;
import org.example.testtaker.model.QuestionMedia;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Service
public class QuestionMediaService {

    public QuestionMedia createPresignedUrl(QuestionMediaUploadRequest questionMediaUploadRequest) {
        /* Create a presigned URL to use in a subsequent PUT request */
            try (S3Presigner presigner = S3Presigner.create()) {
                PutObjectRequest objectRequest = PutObjectRequest.builder()
                        .bucket("take-test-bucket")
                        .key(questionMediaUploadRequest.getKey())
                        .build();

                PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                        .signatureDuration(Duration.ofMinutes(10))  // The URL expires in 10 minutes.
                        .putObjectRequest(objectRequest)
                        .build();


                PresignedPutObjectRequest presignedRequest = presigner.presignPutObject(presignRequest);
                String myURL = presignedRequest.url().toString();

                return new QuestionMedia(presignedRequest.url().toExternalForm());
        }
    }
}

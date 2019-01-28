package io.pivotal.pfs.imagedemo.faces;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class DetectFaces {

    AmazonRekognition amazonRekognition;

    private static String BUCKET_NAME = "pfs-rekognition";

    public DetectFaces(AmazonRekognition amazonRekognition) {
        this.amazonRekognition = amazonRekognition;
    }

    public DetectFacesResult requestFaceDetection(String name) {
        DetectFacesRequest request = new DetectFacesRequest()
                .withImage(new Image().withS3Object(new S3Object()
                        .withName(name).withBucket(BUCKET_NAME)))
                .withAttributes(Attribute.ALL);
        return amazonRekognition.detectFaces(request);
    }
}

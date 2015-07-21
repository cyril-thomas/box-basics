package com.simplyct.woddojo.helper.amazon;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cyril on 11/14/14.
 */
@Service
public class AwsS3Service {
    private AmazonS3 s3;
    private final Region usWest2          = Region.getRegion(Regions.US_WEST_2);
    private       String DEFAULT_FILENAME = "original";

    @Autowired
    private AwsS3Credentials credentials;

    @PostConstruct
    public void init() {
        s3 = new AmazonS3Client(credentials.getCredentials());
        s3.setRegion(usWest2);
    }

    /**
     * Service method to be used to upload resources to the cloud.
     *
     * @param inputStream              - Required
     * @param orgId                    - Required
     * @param uniqueId                 - Required
     * @param fileExt                  - Optional
     * @param contentType              - Required
     * @param metadata                 - Optional
     * @param bucketName               - Required
     * @param objectTemplate           - Required
     * @param breakCache               - Optional, used to rewrite the existing resource for the same unique id
     * @return Uri representing the object uploaded
     */
    public String uploadResource(InputStream inputStream,
                                 Long orgId,
                                 Long uniqueId,
                                 String fileExt,
                                 String contentType,
                                 ObjectMetadata metadata,
                                 String bucketName,
                                 String objectTemplate,
                                 boolean breakCache) {

        if (inputStream == null) {
            throw new IllegalArgumentException("Cannot not upload resource, no content found");
        }

        if (uniqueId == null || contentType == null) {
            throw new IllegalArgumentException(
                    String.format("Cannot not upload resource, illegal argument uniqueId : %s, ContentType : %s",
                                  uniqueId, contentType
                    ));
        }

        if (StringUtils.isEmpty(bucketName) || StringUtils.isEmpty(objectTemplate)) {
            throw new IllegalArgumentException(
                    String.format("Cannot not upload resource, illegal argument bucketName : %s, objectTemplate : %s",
                                  bucketName, objectTemplate
                    ));
        }

        String objectName = generateObjectName(orgId,uniqueId, fileExt, breakCache, objectTemplate);

        metadata = setUpObjectMetadata(inputStream, contentType, metadata);

        uploadStreamToBucket(bucketName, objectName, inputStream, contentType, metadata);

        return objectName;
    }


    private String generateObjectName(Long orgId,Long uniqueId, String fileExt, boolean breakCache, String objectTemplate) {
        String fileName = generateFileName(fileExt, breakCache);
        return String.format(objectTemplate,
                             orgId,uniqueId, fileName);
    }

    private String generateFileName(String fileExt, boolean breakCache) {

        String fileName = breakCache ? DEFAULT_FILENAME.concat("-" + Math.random()) : DEFAULT_FILENAME;
        if (StringUtils.isNotEmpty(fileExt)) {
            fileName = fileName.concat(fileExt.contains(".") ? fileExt : ".".concat(fileExt));
        }
        return fileName;
    }

    private ObjectMetadata setUpObjectMetadata(InputStream inputStream, String contentType, ObjectMetadata metadata) {
        if (metadata == null) {
            metadata = new ObjectMetadata();
        }
        if (metadata.getContentType() == null) {
            metadata.setContentType(contentType);
        }
        if (metadata.getContentLength() <= 0) {
            metadata.setContentLength(getContentLength(inputStream));
        }
        return metadata;
    }

    private Integer getContentLength(InputStream inputStream) {
        Integer available = null;
        try {
            available = inputStream.available();
        } catch (IOException e) {
        }
        return available;
    }

    private void uploadStreamToBucket(String bucketName,
                                      String objectName,
                                      InputStream inputStream,
                                      String contentType,
                                      ObjectMetadata metadata) {
        if (metadata == null) {
            metadata = new ObjectMetadata();
        }

        if (metadata.getContentType() == null) {
            metadata.setContentType(contentType);
        }

        s3.putObject(new PutObjectRequest(bucketName, objectName, inputStream, metadata));
    }
}

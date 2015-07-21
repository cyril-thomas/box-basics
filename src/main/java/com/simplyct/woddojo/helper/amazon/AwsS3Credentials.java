package com.simplyct.woddojo.helper.amazon;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by cyril on 7/20/15.
 */
@Service("credentials")
public class AwsS3Credentials {


    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    public AWSCredentials getCredentials() {
        AWSCredentials credentials = null;
        try {
            credentials = new BasicAWSCredentials(accessKey, secretKey);
        } catch (Exception e) {
            throw new AmazonClientException(
                    String.format("Cannot load the credentials for the specified accesskey :%s and secretkey: %s", accessKey, secretKey),
                    e);
        }
        return credentials;
    }
}

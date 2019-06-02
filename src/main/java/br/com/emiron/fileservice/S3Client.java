package br.com.emiron.fileservice;

import br.com.emiron.fileservice.infra.S3Properties;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;

@Component
@RequiredArgsConstructor
public class S3Client{
    private AmazonS3 client;

    private final S3Properties properties;


    @PostConstruct
    private void setupAWS(){
        BasicAWSCredentials credential = new BasicAWSCredentials(properties.getKey(), properties.getSecret());
        this.client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credential))
                .withRegion(properties.getRegion())
                .build();
    }

    public URL store(byte[] file, String fileKey, MimeType contentType) {

        InputStream stream = new ByteArrayInputStream(file);
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(file.length);
        meta.setContentType(contentType.getType());

        client.putObject(properties.getBucket(), fileKey, stream, meta);

        return getFileUrl(fileKey);
    }

    private URL getFileUrl(String fileKey) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(properties.getBucket(), fileKey);
        generatePresignedUrlRequest.setMethod(HttpMethod.GET);

        return client.generatePresignedUrl(generatePresignedUrlRequest);
    }
}

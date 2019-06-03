package br.com.emiron.fileservice.infra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("s3")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class S3Properties {

    private String bucket;
    private String key;
    private String secret;
    private String region;
}
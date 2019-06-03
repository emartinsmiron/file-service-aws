package br.com.emiron.fileservice;

import com.amazonaws.services.xray.model.Http;
import com.amazonaws.util.Md5Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {

    private final S3Client client;

    public ResponseEntity upload(MultipartFile multipartFile) {
        try {
            return new ResponseEntity(client.store(multipartFile.getBytes(),
                        Md5Utils.md5AsBase64(multipartFile.getBytes()),
                        MimeType.valueOf(multipartFile.getContentType())),HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity getFileUrl(String key){
        return new ResponseEntity(client.getFileUrl(key), HttpStatus.OK);

    }
}

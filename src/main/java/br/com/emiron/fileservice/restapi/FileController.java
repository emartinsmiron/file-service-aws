package br.com.emiron.fileservice.restapi;

import br.com.emiron.fileservice.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService service;


    @PostMapping
    private ResponseEntity<URL> upload(@RequestParam("file") final MultipartFile file) {
        return new ResponseEntity(service.upload(file),HttpStatus.CREATED);
    }

}

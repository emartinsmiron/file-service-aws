package br.com.emiron.fileservice.restapi;

import br.com.emiron.fileservice.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    private ResponseEntity<URL> getFileUrl(@RequestParam("file") final String key) {
        return new ResponseEntity(service.getFileUrl(key),HttpStatus.CREATED);
    }

}

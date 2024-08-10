package com.BookStore.BookManageService.controller;

import com.BookStore.BookManageService.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/quan-ly-sach-service/hinh-anh/")
public class HinhAnhController {
    @Autowired
    private FileService fileService;

//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        try {
//            fileService.saveFile(file,0);
//            return ResponseEntity.ok("File uploaded successfully");
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
//        }
//    }

    @GetMapping(value = "get")
    public ResponseEntity<InputStreamResource> getImageDynamicType(@RequestParam("name") String name) {
        MediaType contentType = MediaType.IMAGE_PNG;
        return ResponseEntity.ok()
                .contentType(contentType)
                .body(fileService.openFile(name,0));
    }


    @GetMapping(value = "getUser")
    public ResponseEntity<InputStreamResource> getImageDynamicTypeUser(@RequestParam("name") String name) {
        MediaType contentType = MediaType.IMAGE_PNG;
        return ResponseEntity.ok()
                .contentType(contentType)
                .body(fileService.openFile(name,1));
    }
}

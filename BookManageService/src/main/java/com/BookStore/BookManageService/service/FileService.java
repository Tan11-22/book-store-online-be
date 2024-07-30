package com.BookStore.BookManageService.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    @Value("${file.images-sach-dir}")
    private String imagesSachDir;

    @Value("${file.images-user-dir}")
    private String imagesUserDir;

    /***
     *
     * @param file
     * @param type type = 0 : lưu ảnh sách, type = 1: lưu ảnh user
     * @throws IOException
     */
    public void saveFile(MultipartFile file,int type, String fileName) throws  IOException {
        String imageDir = (type == 0) ? imagesSachDir: imagesUserDir;
        // Tạo đường dẫn tương đối từ thư mục gốc của project
        Path directoryPath = Paths.get(imageDir).toAbsolutePath().normalize();

        // Tạo thư mục nếu chưa tồn tại
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        // Xác định đường dẫn tệp
        Path filePath = directoryPath.resolve(fileName).normalize();

        // Lưu tệp
        file.transferTo(filePath.toFile());
    }

    public InputStreamResource openFile(String name, int type) {
        String imageDir = (type == 0) ? imagesSachDir: imagesUserDir;
        try {
            File file = new File(imageDir, name);
            InputStream result = new FileInputStream(file);
            System.out.println(imageDir+ "/" + name);
            return new InputStreamResource(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

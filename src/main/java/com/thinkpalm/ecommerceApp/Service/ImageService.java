package com.thinkpalm.ecommerceApp.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {
    @Value("${image.upload.directory}")
    private String uploadDirectory;

    public byte[] viewImage(Integer prodId,String filename) throws IOException {
        String filePath = uploadDirectory +"/"+prodId+"/"+filename;
        Path path = Paths.get(filePath);
        byte[] imageBytes = Files.readAllBytes(path);
        return imageBytes;
    }

}

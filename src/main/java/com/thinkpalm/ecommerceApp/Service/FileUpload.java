package com.thinkpalm.ecommerceApp.Service;

import com.thinkpalm.ecommerceApp.Model.Product;
import com.thinkpalm.ecommerceApp.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUpload {
    @Autowired
        private final ProductRepo productRepo;

        @Value("${image.upload.directory}")
        private String uploadDirectory;
        @Autowired
    public FileUpload(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public void uploadImage(Integer productId, MultipartFile imageFile) {
        String filePath = uploadDirectory + "/" + productId + "/";
        Product product = productRepo.findById(productId).orElse(null);
        if (product != null) {
            try {
                File directory = new File(filePath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                String originalFilename = productId + imageFile.getOriginalFilename();
                product.setImage(originalFilename);
                productRepo.save(product);
                Path path = Paths.get(filePath, originalFilename);
                byte[] bytes = imageFile.getBytes();
                Files.write(path, bytes);

                String relativePath = productId + "/" + originalFilename;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
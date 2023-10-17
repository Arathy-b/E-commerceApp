package com.thinkpalm.ecommerceApp.Service;


import com.thinkpalm.ecommerceApp.Model.*;
import com.thinkpalm.ecommerceApp.Repository.CategoryRepo;
import com.thinkpalm.ecommerceApp.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
@Service
public class AdminService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ProductRepo productRepo;
    public String createCategory(Map<String,Object> data) {
        Category category = new Category();
        category.setName(data.get("name").toString());
        category.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));

        categoryRepo.save(category);
        return "category created";
    }
    public String createProducts(CreateProductRequest createProductRequest) {
        Optional<Category>cat = categoryRepo.findById(createProductRequest.getCategory_id());
        Product product=new Product();
        product.setTitle(createProductRequest.getTitle());
        product.setDescription(createProductRequest.getDescription());
        product.setQuantity(createProductRequest.getQuantity());
        product.setPrice(createProductRequest.getPrice());
        product.setCategory(cat.get());
        product.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));

        productRepo.save(product);
        return "product created";
    }
}

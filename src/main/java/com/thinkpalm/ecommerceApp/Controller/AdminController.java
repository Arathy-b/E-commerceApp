package com.thinkpalm.ecommerceApp.Controller;


import com.thinkpalm.ecommerceApp.Model.Category;
import com.thinkpalm.ecommerceApp.Model.CreateProductRequest;
import com.thinkpalm.ecommerceApp.Model.Product;
import com.thinkpalm.ecommerceApp.Repository.CategoryRepo;
import com.thinkpalm.ecommerceApp.Repository.ProductRepo;
import com.thinkpalm.ecommerceApp.Service.AdminService;
import com.thinkpalm.ecommerceApp.Service.FileUpload;
import com.thinkpalm.ecommerceApp.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private AdminService adminService;
    @Autowired
    private  ProductRepo productRepo;
    @Autowired
    private ProductService productService;
    @Autowired
    private  FileUpload fileUpload;


    @PostMapping("/createCategory")
    public String createCategory(@RequestBody Map<String, Object> data){
        return adminService.createCategory(data);
    }

    @GetMapping("/listCategory")
    public List<Category> listCategory(){
        return adminService.listCategory();
    }
    @PostMapping("/createProduct")
    public Product createProducts(@RequestBody CreateProductRequest request){
        return adminService.createProducts(request);
    }
    @GetMapping("/listProducts")
    public List<Product> listAllProducts() {
        return adminService.listAllProducts();
    }

    @PostMapping(path = "/uploadImage/{productId}")
    public ResponseEntity<String> uploadImage(@PathVariable Integer productId, @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            fileUpload.uploadImage(productId, imageFile);
            return ResponseEntity.ok("Image uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to upload image: " + e.getMessage());
        }
    }


    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer productId){
        adminService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted succesfully");
    }
    @PutMapping("/update/{productId}")
    public Product updateProduct(@PathVariable Integer productId,@RequestBody CreateProductRequest newproduct){
        Product updateProduct=adminService.updateProduct(productId,newproduct);
        return  updateProduct;
    }


}

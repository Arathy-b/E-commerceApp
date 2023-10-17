package com.thinkpalm.ecommerceApp.Controller;


import com.thinkpalm.ecommerceApp.Model.Category;
import com.thinkpalm.ecommerceApp.Model.CreateProductRequest;
import com.thinkpalm.ecommerceApp.Model.Product;
import com.thinkpalm.ecommerceApp.Repository.CategoryRepo;
import com.thinkpalm.ecommerceApp.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private AdminService adminService;

    @PostMapping("/createCategory")
    public String createCategory(@RequestBody Map<String, Object> data){
        return adminService.createCategory(data);
    }

    @PostMapping("/createProduct")
    public String createProducts(@RequestBody CreateProductRequest request){
        return adminService.createProducts(request);
    }





}

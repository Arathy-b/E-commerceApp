package com.thinkpalm.ecommerceApp.Service;

import com.thinkpalm.ecommerceApp.Model.*;
import com.thinkpalm.ecommerceApp.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class AdminService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private  CategoryRepo catRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderRepo orderRepo;

    public String createCategory(Map<String,Object> data) {
        Category category = new Category();
        category.setName(data.get("name").toString());
        category.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));
        categoryRepo.save(category);
        return "category created";
    }

    public List<Product> listAllProducts() {
        return productRepo.findAll();
    }
    public Product  createProducts(CreateProductRequest createProductRequest) {
        Optional<Category>cat = categoryRepo.findById(createProductRequest.getCategory_id());
        Product product=new Product();
        product.setTitle(createProductRequest.getTitle());
        product.setDescription(createProductRequest.getDescription());
        product.setQuantity(createProductRequest.getQuantity());
        product.setPrice(createProductRequest.getPrice());
        product.setCategory(cat.get());
        product.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));

        productRepo.save(product);
        return product;
    }
    public void deleteProduct(Integer productId) {
        productRepo.deleteById(productId);
    }

  
    public Product updateProduct(Integer productId,CreateProductRequest newproduct) {
        Product oldProduct=productRepo.findById(productId).orElse(null);
        oldProduct.setImage(newproduct.getImage()!=null ?  newproduct.getImage() : oldProduct.getImage());
        oldProduct.setTitle(newproduct.getTitle()!=null ?  newproduct.getTitle() : oldProduct.getTitle());
        oldProduct.setDescription(newproduct.getDescription()!=null ?  newproduct.getDescription() : oldProduct.getDescription());
        oldProduct.setPrice(newproduct.getPrice()!=null ?  newproduct.getPrice() : oldProduct.getPrice());
        if(newproduct.getCategory_id()!=null){
            oldProduct.setCategory(catRepo.findById(newproduct.getCategory_id()).orElse(oldProduct.getCategory()));
        }else{
            oldProduct.setCategory(oldProduct.getCategory());
        }
        oldProduct.setQuantity(newproduct.getQuantity()!=null ?  newproduct.getQuantity() : oldProduct.getQuantity());
        oldProduct.setModified_at(Timestamp.valueOf(LocalDateTime.now()));
        Product save=productRepo.save(oldProduct);
        return  save;
    }

    public List<Category> listCategory() {
        return categoryRepo.findAll();
    }

    public List<Customer> getAllCustomers(){
        return customerRepo.listAllCustomers();
    }

    public List<Map<String,Object>> getAllOrderDetails() {
        List<Map<String,Object>> orderDetails = orderRepo.getAllOrders();

        return orderDetails;
    }

    public List<Map<String,Integer>> getCountOfAll(){
        List<Map<String,Integer>> count=orderRepo.getCountOfAll();
        return count;
    }


    public String changeOrderStatus(Integer orderId) {
        Order order = orderRepo.findById(orderId).orElse(null);
        if(order!=null) {
            if (order.getStatus()==OrderStatus.PLACED){
                order.setStatus(OrderStatus.SHIPPED);
            } else if (order.getStatus()==OrderStatus.SHIPPED) {
                order.setStatus(OrderStatus.DELIVERED);
            }else{
                return order.getStatus().toString();
            }
            orderRepo.save(order);
            return order.getStatus().toString();
        }else {
            throw new RuntimeException();
        }
    }
}

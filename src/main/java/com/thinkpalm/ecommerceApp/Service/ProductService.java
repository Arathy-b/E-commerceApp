package com.thinkpalm.ecommerceApp.Service;

import com.thinkpalm.ecommerceApp.Model.*;
import com.thinkpalm.ecommerceApp.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class ProductService {
    private final ProductRepo productRepo;
    private final CartRepo cartRepo;
    private final CartProductRepo cartProductRepo;
    private final CustomerRepo customerRepo;
    private final CategoryRepo catRepo;
    @Autowired
    public ProductService(ProductRepo productRepo,
                          CartRepo cartRepo,
                          CartProductRepo cartProductRepo,
                          CustomerRepo customerRepo,
                          CategoryRepo catRepo)
    {
        this.productRepo = productRepo;
        this.cartRepo = cartRepo;
        this.cartProductRepo = cartProductRepo;
        this.customerRepo = customerRepo;
        this.catRepo=catRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
    public  Product viewProductById(Integer productId){
        Product prod=productRepo.findById(productId).orElse(null);
        return prod;
    }
    public String getImageRelativePath(Integer postId) {
        return "/Users/arathy.h/Downloads/ecommerce-website";
    }


    //OPERATIONS IN CART
    public String addProductToCart(Integer prodId,Integer quantity) {
        Customer customer = customerRepo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
        Cart custCart = cartRepo.findByCustId(customer.getId());
        if (custCart == null) {
            Cart newCart = createCart(customer);
            Product product = productRepo.findById(prodId).orElse(null);
            if (product != null) {
                CartProduct cartProduct = new CartProduct();
                cartProduct.setCart(newCart);
                cartProduct.setProduct(product);
                cartProduct.setQuantity(quantity);
                cartProduct.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));
                cartProductRepo.save(cartProduct);
                return "successfull";
            } else {
                return "invalid product!";
            }
        } else {
            Product product = productRepo.findById(prodId).orElse(null);
            if (product != null) {
                CartProduct cartProduct = new CartProduct();
                cartProduct.setCart(custCart);
                cartProduct.setProduct(product);
                cartProduct.setQuantity(quantity);
                cartProduct.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));
                cartProductRepo.save(cartProduct);
                return "successfull";
            } else {
                return "invalid product!";
            }
        }
    }

    private Cart createCart(Customer customer) {
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));
        cartRepo.save(cart);
        return cart;
    }
    public List<Map<String, Object>> getProductsInCart(Integer cartId) {
        List<Map<String, Object>> res = productRepo.getAllCartProducts(cartId);
        return res;
    }
    public void removeProductFromCart(Integer cartId, Integer productId) {
        cartProductRepo.deleteByCartIdAndProductId(cartId, productId);
    }
    public void updateProductQuantityInCart(Integer cartId, Integer productId, int newQuantity) {
        cartProductRepo.updateQuantityByCartIdAndProductId(cartId, productId, newQuantity);
    }
    public List<Product>findProductByCategory(Integer catId){
        Category cat=this.catRepo.findById(catId).orElseThrow();
        List<Product>findByCategory=this.productRepo.findByCategory(cat);
        return findByCategory;
    }

    public List<Product> getTrendingProducts() {
        return productRepo.getTrending();
    }
}
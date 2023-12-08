package com.thinkpalm.ecommerceApp.Controller;
import com.thinkpalm.ecommerceApp.Model.CreateProductRequest;
import com.thinkpalm.ecommerceApp.Model.Product;
import com.thinkpalm.ecommerceApp.Model.QuantityRequest;
import com.thinkpalm.ecommerceApp.Repository.ProductRepo;
import com.thinkpalm.ecommerceApp.Service.FileUpload;
import com.thinkpalm.ecommerceApp.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductRepo productRepo;
    private final ProductService productService;
    @Autowired
    public ProductsController(ProductRepo productRepo, ProductService productService) {
        this.productRepo = productRepo;
        this.productService = productService;

    }
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/trending")
    public List<Product> getTrendingProducts() {
        return productService.getTrendingProducts();
    }
    @GetMapping("/View/{productId}")
    public Product viewProductById(@PathVariable Integer productId){
        Product product1= productService.viewProductById(productId);
        return  product1;
    }
    @GetMapping("/category/{catId}")
    public ResponseEntity<List<Product>>getProductByCategory(@PathVariable Integer catId){
        List<Product>findProductByCategory=this.productService.findProductByCategory(catId);
        return new ResponseEntity<List<Product>>(findProductByCategory, HttpStatus.ACCEPTED);
    }
    @GetMapping(path = "/getImage/{productId}")
    public ResponseEntity<String> getImageRelativePath(@PathVariable Integer productId) {
        String relativePath = productService.getImageRelativePath(productId);
        if (relativePath != null) {
            return ResponseEntity.ok(relativePath);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


//CRUD OPERATIONS IN CART

    @PostMapping("/addProductToCart/{prodId}")
    public ResponseEntity<String> addProductToCart(@PathVariable Integer prodId,@RequestBody Map<String,Integer> quantity)
    {
        return ResponseEntity.ok(productService.addProductToCart(prodId,quantity.get("quantity")));
    }
    @GetMapping("/{cartId}/products")
    public ResponseEntity<List<Map<String,Object>>> getProductsInCart(@PathVariable Integer cartId) {
        return ResponseEntity.ok(productService.getProductsInCart(cartId));
    }
    @PutMapping("/updateQuantity")
    public ResponseEntity<String> updateProductQuantityInCart(@RequestBody QuantityRequest quantityRequest) {
        productService.updateProductQuantityInCart(quantityRequest.getCartId(), quantityRequest.getProductId(), quantityRequest.getNewQuantity());
        return ResponseEntity.ok("Product quantity updated successfully");
    }
    @DeleteMapping("/delete/{cartId}/{productId}")
    public ResponseEntity<String> deleteCart(@PathVariable Integer cartId,@PathVariable Integer productId ) {
        productService.removeProductFromCart(cartId,productId);
        return ResponseEntity.ok("Product removed successfully");
    }


}
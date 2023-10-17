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
    private final FileUpload fileUpload;
    @Autowired
    public ProductsController(ProductRepo productRepo, ProductService productService, FileUpload fileUpload) {
        this.productRepo = productRepo;
        this.productService = productService;
        this.fileUpload = fileUpload;
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
    @GetMapping(path = "/getImage/{productId}")
    public ResponseEntity<String> getImageRelativePath(@PathVariable Integer productId) {
        String relativePath = productService.getImageRelativePath(productId);
        if (relativePath != null) {
            return ResponseEntity.ok(relativePath);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/View/{productId}")
    public Product viewProductById(@PathVariable Integer productId){
    Product product1= productService.viewProductById(productId);
    return  product1;
    }
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer productId){
    productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted succesfully");
    }
    @PutMapping("/update/{productId}")
    public Product updateProduct(@PathVariable Integer productId,@RequestBody CreateProductRequest newproduct){
    Product updateProduct=productService.updateProduct(productId,newproduct);
    return  updateProduct;
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
    @DeleteMapping("/delete/{cartId}/{productId}")
    public ResponseEntity<String> deleteCart(@PathVariable Integer cartId,@PathVariable Integer productId ) {
        productService.removeProductFromCart(cartId,productId);
        return ResponseEntity.ok("Product removed successfully");
    }
    @PutMapping("/updateQuantity")
    public ResponseEntity<String> updateProductQuantityInCart(@RequestBody QuantityRequest quantityRequest) {
        productService.updateProductQuantityInCart(quantityRequest.getCartId(), quantityRequest.getProductId(), quantityRequest.getNewQuantity());
        return ResponseEntity.ok("Product quantity updated successfully");
    }
    @GetMapping("/category/{catId}")
    public ResponseEntity<List<Product>>getProductByCategory(@PathVariable Integer catId){
        List<Product>findProductByCategory=this.productService.findProductByCategory(catId);
        return new ResponseEntity<List<Product>>(findProductByCategory, HttpStatus.ACCEPTED);
}
}





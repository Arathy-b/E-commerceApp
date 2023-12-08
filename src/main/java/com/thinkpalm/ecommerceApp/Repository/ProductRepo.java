package com.thinkpalm.ecommerceApp.Repository;

import com.thinkpalm.ecommerceApp.Model.Category;
import com.thinkpalm.ecommerceApp.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ProductRepo extends JpaRepository<Product,Integer> {
    @Query(value = "select p.title,p.description,p.price,p.category_id from cart_product as cp inner join product as p on cp.product_id =p.id where cp.cart_id=?",nativeQuery = true)
    List<Map<String,Object>> getAllCartProducts(Integer cart_id);

    List<Product> findByCategory(Category category);

    @Query(value = "SELECT * FROM ecommercedb.product\n" +
            "ORDER BY RAND()\n" +
            "LIMIT 4;",nativeQuery = true)
    List<Product> getTrending();

}

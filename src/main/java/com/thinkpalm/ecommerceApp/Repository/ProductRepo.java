package com.thinkpalm.ecommerceApp.Repository;

import com.thinkpalm.ecommerceApp.Model.Category;
import com.thinkpalm.ecommerceApp.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ProductRepo extends JpaRepository<Product,Integer> {
    @Query(value = "select c.id as cartId,p.id,p.title,p.description,p.price,p.category_id,cp.quantity,p.image from ecommercedb.cart as c inner join ecommercedb.cart_product as cp on c.id = cp.cart_id inner join ecommercedb.product p on cp.product_id = p.id where c.customer_id=?1",nativeQuery = true)
    List<Map<String,Object>> getAllCartProducts(Integer custId);

    List<Product> findByCategory(Category category);

    @Query(value = "SELECT * FROM ecommercedb.product\n" +
            "ORDER BY RAND()\n" +
            "LIMIT 4;",nativeQuery = true)
    List<Product> getTrending();

}

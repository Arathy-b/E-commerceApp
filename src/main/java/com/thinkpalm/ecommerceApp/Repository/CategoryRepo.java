package com.thinkpalm.ecommerceApp.Repository;

import com.thinkpalm.ecommerceApp.Model.Category;
import com.thinkpalm.ecommerceApp.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
    Category findById(Category category);
    @Query(value = "SELECT * FROM ecommercedb.product where category_id=?1 ORDER BY RAND() LIMIT 4",nativeQuery = true)
    List<Map<String,Object>> findTrendingProductsByCategory(Integer catId );
}


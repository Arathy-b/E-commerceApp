package com.thinkpalm.ecommerceApp.Repository;

import com.thinkpalm.ecommerceApp.Model.Category;
import com.thinkpalm.ecommerceApp.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
    Category findById(Category category);
}


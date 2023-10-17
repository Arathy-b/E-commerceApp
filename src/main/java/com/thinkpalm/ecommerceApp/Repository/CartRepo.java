package com.thinkpalm.ecommerceApp.Repository;

import com.thinkpalm.ecommerceApp.Model.Cart;
import com.thinkpalm.ecommerceApp.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart,Integer> {

    @Query(value = "select * from cart where customer_id = ?",nativeQuery = true)
    Cart findByCustId(Integer id);
}

package com.thinkpalm.ecommerceApp.Repository;

import com.thinkpalm.ecommerceApp.Model.CartProduct;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartProductRepo extends JpaRepository<CartProduct,Integer> {
//    SELECT p.title,p.description,p.price  FROM ecommercedb.cart_product as cp inner join ecommercedb.product as p on cp.product_id = p.id;
    @Modifying
    @Transactional
    @Query("DELETE FROM CartProduct cp WHERE cp.cart.id = :cartId AND cp.product.id = :productId")
    void deleteByCartIdAndProductId(@Param("cartId") Integer cartId, @Param("productId") Integer productId);
    @Modifying
    @Transactional
    @Query("UPDATE CartProduct cp SET cp.quantity = :quantity WHERE cp.cart.id = :cartId AND cp.product.id = :productId")
    void updateQuantityByCartIdAndProductId(@Param("cartId") Integer cartId, @Param("productId") Integer productId, @Param("quantity") int quantity);
}


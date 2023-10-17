package com.thinkpalm.ecommerceApp.Repository;

import com.thinkpalm.ecommerceApp.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItem,Integer> {
}

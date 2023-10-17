package com.thinkpalm.ecommerceApp.Repository;

import com.thinkpalm.ecommerceApp.Model.Customer;
import com.thinkpalm.ecommerceApp.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Integer> {

//    List<Order> findByUserOrderByOrderDateDesc(Customer customer);
}

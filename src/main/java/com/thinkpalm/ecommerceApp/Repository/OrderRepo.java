package com.thinkpalm.ecommerceApp.Repository;

import com.thinkpalm.ecommerceApp.Model.Customer;
import com.thinkpalm.ecommerceApp.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface OrderRepo extends JpaRepository<Order,Integer> {
    @Query(value = "SELECT co.*,count(*) as noOfItems FROM ecommercedb.cust_order as co \n" +
            "\tinner join ecommercedb.order_item as oi on co.id = oi.order_id order by co.order_date;",nativeQuery = true)
    List<Map<String,Object>> getAllOrders();
}

package com.thinkpalm.ecommerceApp.Repository;

import com.thinkpalm.ecommerceApp.Model.Customer;
import com.thinkpalm.ecommerceApp.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order,Integer> {
    @Query(value = "SELECT co.*,count(*) as itemCount,c.id as custId FROM ecommercedb.cust_order as co\n" +
            "            inner join ecommercedb.order_item as oi on co.id = oi.order_id\n" +
            "            inner join ecommercedb.address as a on a.id = co.address_id\n" +
            "            inner join ecommercedb.customer as c on c.id = a.cust_id\n" +
            "            group by oi.order_id\n" +
            "            order by co.order_date;",nativeQuery = true)
    List<Map<String,Object>> getAllOrders();
@Query(value = "select oi.*,a.*,c.id as custId,c.name as custName,p.title from ecommercedb.order_item as oi\n" +
        "\t inner join ecommercedb.product as p on p.id = oi.product_id\n" +
        "     inner join ecommercedb.cust_order as o on o.id = oi.order_id\n" +
        "     inner join ecommercedb.address as a on a.id = o.address_id\n" +
        "     inner join ecommercedb.customer as c on c.id = a.cust_id\n" +
        "    where c.id = ?1 order by o.order_date desc",nativeQuery = true)
    List<Map<String,Object>> getCustomerOrders(Integer userId);

@Query(value = "SELECT oi.product_id, oi.price, p.title FROM ecommercedb.order_item oi INNER JOIN ecommercedb.product p ON oi.product_id = p.id where oi.order_id = ?1",nativeQuery = true)
List<Map<String,Object>> getOrdersDetails(Integer orderId);






}

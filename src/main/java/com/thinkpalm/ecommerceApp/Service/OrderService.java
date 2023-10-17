package com.thinkpalm.ecommerceApp.Service;

import com.thinkpalm.ecommerceApp.Model.*;
import com.thinkpalm.ecommerceApp.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderItemRepo orderItemRepo;
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CartProductRepo cartProductRepo;
    @Autowired
    private ProductRepo productRepo;
    public Order linkAddress(Integer orderId, Integer addressId) {
        Order order = orderRepo.findById(orderId).orElseThrow();
        Address address = addressRepo.findById(addressId).orElseThrow();
        order.setAddress(address);
        return orderRepo.save(order);
    }
    public Order placeOrder(OrderRequest orderRequest) {
        Customer currentCustomer = customerRepo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
        Address address= orderRequest.getAddress();
        address.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));
        address.setCustomer(currentCustomer);
        addressRepo.save(address);

        Order order = new Order();
        order.setAddress(address);
        order.setStatus(orderRequest.getOrderStatus());

        float totalPrice = 0;
        for(OrderItem orderItem : orderRequest.getOrderItem()){
            Product product=productRepo.findById(orderItem.getProductId()).orElse(null);
            orderItem.setPrice(product.getPrice());
            totalPrice = totalPrice+orderItem.getPrice();
        }
        order.setTotalPrice(totalPrice);
        order.setOrder_date(Timestamp.valueOf(LocalDateTime.now()));
        order.setOrderItems(orderRequest.getOrderItem());
        orderRepo.save(order);
        return order;
    }

//        for(OrderItem orderItem : orderRequest.getOrderItem()){
//            OrderItem orderItem1 = new OrderItem();
//            orderItem1.setPrice(orderItem.getPrice());
//            orderItem1.setQuantity(orderItem.getQuantity());
//            orderItem1.setProductId(orderItem.getProductId());
////            orderItem1.setOrder(order);
//            orderItemRepo.save(orderItem1);
//        }
    public Optional<Order> getOrderDetails(Integer orderId) {
        Optional<Order> optionalOrder = orderRepo.findById(orderId);
//        optionalOrder.ifPresent(order -> order.getOrderItems().size());
        return optionalOrder;
    }
}

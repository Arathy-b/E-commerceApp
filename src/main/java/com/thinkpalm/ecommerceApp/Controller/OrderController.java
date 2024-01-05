package com.thinkpalm.ecommerceApp.Controller;

import com.thinkpalm.ecommerceApp.Model.*;
import com.thinkpalm.ecommerceApp.Service.CustomerService;
import com.thinkpalm.ecommerceApp.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
@PostMapping("/{orderId}/{addressId}")

public ResponseEntity<Order> linkAddress(@PathVariable Integer orderId,@PathVariable Integer addressId) {
    Order order = orderService.linkAddress(orderId,addressId);
    return new ResponseEntity<>(order, HttpStatus.CREATED);
}


    @PostMapping("/placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.placeOrder(orderRequest);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public List<Map<String,Object>> getOrderDetails(@PathVariable Integer orderId) {
        return orderService.getOrderDetails(orderId);
    }

    @GetMapping("/allOrders/{custId}")
    public List<Map<String,Object>> getAllOrders(@PathVariable Integer custId){
    return orderService.getAllOrders(custId);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Map<String, Object>>> getAllDetails() {
        return new ResponseEntity<List<Map<String, Object>>>(orderService.getAllDetails(),HttpStatus.OK);
    }
    @GetMapping("/createTransaction/{totalPrice}")
    public TransactionDetails createTransaction(@PathVariable(name="totalPrice") Integer totalPrice){
        return orderService.createTransaction(totalPrice);

    }

}
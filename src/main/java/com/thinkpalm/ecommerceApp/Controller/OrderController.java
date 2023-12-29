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
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable Integer orderId) {
        Optional<Order> orderOptional = orderService.getOrderDetails(orderId);
        return orderOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/createTransaction/{amount}")
    public TransactionDetails createTransaction(@PathVariable(name="amount") Double amount){
        return orderService.createTransaction(amount);

    }

}
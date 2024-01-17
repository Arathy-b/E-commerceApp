package com.thinkpalm.ecommerceApp.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class OrderRequest{


    private Address address;
    private List<OrderItem> orderItem;
    private OrderStatus orderStatus;
    private String transactionId;
    private  Integer cartId;

}

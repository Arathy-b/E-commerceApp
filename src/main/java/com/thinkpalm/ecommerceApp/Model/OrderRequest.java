package com.thinkpalm.ecommerceApp.Model;

import java.util.List;

public class OrderRequest{


    private Address address;
    private List<OrderItem> orderItem;
    private OrderStatus orderStatus;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

//    public Integer getCartProductId() {
//        return cartProductId;
//    }
//
//    public void setCartProductId(Integer cartProductId) {
//        this.cartProductId = cartProductId;
//    }
}

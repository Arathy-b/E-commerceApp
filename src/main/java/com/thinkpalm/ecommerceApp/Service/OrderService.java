package com.thinkpalm.ecommerceApp.Service;

import com.razorpay.RazorpayClient;
import com.thinkpalm.ecommerceApp.Model.*;
import com.thinkpalm.ecommerceApp.Repository.*;
import com.thinkpalm.ecommerceApp.Util.AppContext;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class  OrderService {
    private static final String KEY="rzp_test_FM8wL0vEVG1t6x";
    private static final String KEY_SECRET="aBtvO7hnS8EGrplNWDasI9Ag";
    private static final String currency="INR";
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
            totalPrice = totalPrice+ (orderItem.getPrice()* orderItem.getQuantity());
            orderItem.setOrder(order);
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
    public List<Map<String,Object>> getOrderDetails(Integer orderId) {
        List<Map<String,Object>> optionalOrder = orderRepo.getOrdersDetails(orderId);
//        optionalOrder.ifPresent(order -> order.getOrderItems().size());
        return optionalOrder;
    }
    public TransactionDetails createTransaction(Integer totalPrice) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("amount", (totalPrice * 100));
            jsonObject.put("currency", currency);

            RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);
            com.razorpay.Order order = razorpayClient.orders.create(jsonObject);

            TransactionDetails transactionDetails= prepareTransactionDetails(order);
            return transactionDetails;


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    private TransactionDetails prepareTransactionDetails(com.razorpay.Order order){
        String orderId=order.get("id");
        String currency=order.get("currency");
        Integer totalPrice=order.get("amount");


        TransactionDetails transactionDetails=new TransactionDetails(orderId,currency,totalPrice,KEY);
        return transactionDetails;





    }

    public List<Map<String, Object>> getAllDetails() {
        return new ArrayList<>();
    }


    public List<Map<String,Object>> getAllOrders(Integer custId) {
        return orderRepo.getCustomerOrders(custId);
    }
}

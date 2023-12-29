//package com.thinkpalm.ecommerceApp.Service;
//
//
//import com.razorpay.Order;
//import com.razorpay.RazorpayClient;
//import com.thinkpalm.ecommerceApp.Model.TransactionDetails;
//import com.thinkpalm.ecommerceApp.Repository.RazorpayRepo;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class RazorpayService {
//    private static final String KEY="rzp_test_FM8wL0vEVG1t6x";
//    private static final String KEY_SECRET="aBtvO7hnS8EGrplNWDasI9Ag";
//    private static final String currency="INR";
//
//   private final RazorpayRepo repo;
//@Autowired
//    public RazorpayService(RazorpayRepo repo) {
//        this.repo = repo;
//    }
//
//
//    public TransactionDetails createTransaction(Double amount) {
//        try {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("amount", (amount * 100));
//            jsonObject.put("currency", currency);
//
//            RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);
//            Order order = razorpayClient.orders.create(jsonObject);
//
//            return prepareTransactionDetails(order);
//
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }
//        private TransactionDetails prepareTransactionDetails(Order order){
//            String orderId=order.get("id");
//            String currency=order.get("currency");
//            String amount=order.get("amount");
//
//            TransactionDetails transactionDetails=new TransactionDetails(orderId,currency,amount);
//            return transactionDetails;
//
//
//
//
//
//    }
//
//}

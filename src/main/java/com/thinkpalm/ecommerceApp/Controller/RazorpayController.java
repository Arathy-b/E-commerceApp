//package com.thinkpalm.ecommerceApp.Controller;
//
//import com.thinkpalm.ecommerceApp.Model.TransactionDetails;
//import com.thinkpalm.ecommerceApp.Service.RazorpayService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("payment")
//public class RazorpayController {
//    @Autowired
//    RazorpayService service;
//
//    @GetMapping("/createTransaction/{amount}")
//    public TransactionDetails createTransaction(@PathVariable(name="amount") Double amount){
//       return orderservice.createTransaction(amount);
//
//    }
//
//}

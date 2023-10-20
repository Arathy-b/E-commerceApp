package com.thinkpalm.ecommerceApp.Controller;

import com.thinkpalm.ecommerceApp.Model.Customer;
import com.thinkpalm.ecommerceApp.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getCustomerDetails")
    public Map<String,Object>  getCustomerDetails(){
        return customerService.getCustomerDetails();
    }

    @DeleteMapping("/delete/{custId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Integer custId){
        customerService.deleteAccount(custId);
        return ResponseEntity.ok("Customer Account deleted succesfully");
    }


}

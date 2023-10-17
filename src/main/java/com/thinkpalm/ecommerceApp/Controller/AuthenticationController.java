package com.thinkpalm.ecommerceApp.Controller;

import com.thinkpalm.ecommerceApp.Model.Customer;
import com.thinkpalm.ecommerceApp.Model.LoginRequest;
import com.thinkpalm.ecommerceApp.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Customer customer){
        return ResponseEntity.ok(authService.register(customer));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
         return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/hai")
    public ResponseEntity<String> helo(){

        return ResponseEntity.ok("hello");
    }



}

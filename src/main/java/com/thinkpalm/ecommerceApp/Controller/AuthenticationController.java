package com.thinkpalm.ecommerceApp.Controller;

import com.thinkpalm.ecommerceApp.Model.Customer;
import com.thinkpalm.ecommerceApp.Model.LoginRequest;
import com.thinkpalm.ecommerceApp.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthService authService;

//
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationForm form, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            // Handle validation errors and return an error response
//            return ResponseEntity.badRequest().body("Validation errors occurred.");
//        }
//
//        User registeredUser = userService.registerUser(form);
//        return ResponseEntity.ok("User registered successfully");
//    }
//
//    // Other authentication endpoints
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
//        return ResponseEntity.badRequest().body("Validation errors occurred.");
//    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody Customer customer){
        return ResponseEntity.ok(authService.register(customer));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
         return ResponseEntity.ok(authService.login(request));
    }

}

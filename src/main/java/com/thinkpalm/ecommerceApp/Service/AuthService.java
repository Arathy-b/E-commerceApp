package com.thinkpalm.ecommerceApp.Service;

import com.thinkpalm.ecommerceApp.Model.*;
import com.thinkpalm.ecommerceApp.Repository.CustomerRepo;
import com.thinkpalm.ecommerceApp.Repository.RoleRepo;
import com.thinkpalm.ecommerceApp.Repository.TokenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AuthService {
    private final CustomerRepo urep;
    @Autowired
    private final PasswordEncoder passEncode;
    private final RoleRepo roleRepo;
    private final JwtService jSer;
    private final AuthenticationManager authenticationManager;
    private final TokenRepo tokenRepo;
    public Map<String,Object> register(Customer userdetails){
        Map<String,Object> res=new HashMap<>();
        try{
            if(urep.isDuplicateExists(userdetails.getName(),userdetails.getEmail(),userdetails.getPhone_number()).isEmpty()){
                Optional<Role> role = roleRepo.findById(2);
                var customer= Customer.builder()
                        .name(userdetails.getName())
                        .email(userdetails.getEmail().toLowerCase())
                        .phone_number(userdetails.getPhone_number())
                        .password(passEncode.encode(userdetails.getPassword()))
                        .created_at(Timestamp.valueOf(LocalDateTime.now()))
                        .build();
                customer.setRole(role.get());
                urep.save(customer);
                var jwtToken=jSer.generateToken(customer);
                revokedAllUserTokens(customer);
                saveUserToken(customer,jwtToken);
                res.put("response",jwtToken);
                res.put("status",true);
                return res;
            }else {
                res.put("response","Duplicate Entry");
                res.put("status",false);
                return res;
            }
        }
        catch (Exception e){
            res.put("response","Registration unsuccesfull");
            res.put("status",false);
            return res;
        }
    }
    public Map<String,Object> login(LoginRequest request){
        Map<String,Object> res=new HashMap<>();
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            Customer customer=urep.findByEmail(request.getEmail())
                    .orElseThrow();
            var jwtToken=jSer.generateToken(customer);
            revokedAllUserTokens(customer);
            saveUserToken(customer,jwtToken);

            res.put("response",jwtToken);
            res.put("status",true);
            return res;
        }
        catch (Exception e){
            res.put("response","Invalid email/password");
            res.put("status",false);
            return res;
        }
    }
    private  void revokedAllUserTokens(Customer customer){
        var validUserTokens =tokenRepo.findAllValidTokenByUser(customer.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepo.saveAll(validUserTokens);
    }
    private void saveUserToken(Customer customer, String jwtToken) {
        var token= Token.builder()
                .customer(customer)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepo.save(token);
    }
}
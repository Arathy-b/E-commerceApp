package com.thinkpalm.ecommerceApp.Repository;

import com.thinkpalm.ecommerceApp.Model.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerRepoTest {
    @Autowired
    private CustomerRepo customerRepo;
//    @Test
//    void saveCustomer(){
//        Customer customer=new Customer();
//        customer.setName("Sandhya");
//        customer.setEmail("sandhya@gmail.com");
//        customer.setPassword("Sandhya@123");
//        customer.setPhone_number("1234567890");
//        customer.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));
//        customerRepo.save(customer);
//        Assertions.assertThat(customer.getId()).isGreaterThan(0);
//    }
    @Test
    void findByEmail() {
        Customer customer=customerRepo.findByEmail("sandhya@gmail.com").orElse(null);
        Assertions.assertThat(customer.getEmail()).isEqualTo("sandhya@gmail.com");

    }
    @Test
    void findEmailNotPresent() {
        Customer customer=customerRepo.findByEmail("sa@gmail.com").orElse(null);
        Assertions.assertThat(customer).isEqualTo(null);

    }

    @Test
   void isDuplicatePhoneExists() {
        Customer customer=new Customer();
        customer.setName("Sandhya");
        customer.setEmail("sandhya@gmail.com");
        customer.setPassword("Sandhya@123");
        customer.setPhone_number("1234567890");
        customer.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));
        Throwable exception = assertThrows(org.springframework.dao.DataIntegrityViolationException.class,() -> customerRepo.save(customer));
        assertEquals("could not execute statement [Duplicate entry '1234567890' for key 'customer.phoneNo_UNIQUE'] [insert into customer (created_at,email,modified_at,name,password,phone_number,role_id,id) values (?,?,?,?,?,?,?,?)]; SQL [insert into customer (created_at,email,modified_at,name,password,phone_number,role_id,id) values (?,?,?,?,?,?,?,?)]; constraint [customer.phoneNo_UNIQUE]",exception.getMessage());
   }

}
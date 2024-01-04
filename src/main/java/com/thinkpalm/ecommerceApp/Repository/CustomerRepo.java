package com.thinkpalm.ecommerceApp.Repository;

import com.thinkpalm.ecommerceApp.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByEmail(String email);

    @Query(value = "SELECT * FROM ecommercedb.customer where role_id=2;",nativeQuery = true)
    List<Customer> listAllCustomers();

    @Query(value = "Select * from customer where name=?1 or email=?2 or phone_number=?3",nativeQuery = true)
    List<Customer> isDuplicateExists(String name, String email, String phoneNumber);
}

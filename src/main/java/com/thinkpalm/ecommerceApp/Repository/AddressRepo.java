package com.thinkpalm.ecommerceApp.Repository;

import com.thinkpalm.ecommerceApp.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepo extends JpaRepository<Address,Integer> {

    @Query(value = "select * from address where cust_id = ?",nativeQuery = true)
    List<Address> findByCustId(Integer id);
}

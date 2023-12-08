package com.thinkpalm.ecommerceApp.Service;

import com.thinkpalm.ecommerceApp.Model.Address;
import com.thinkpalm.ecommerceApp.Model.Customer;
import com.thinkpalm.ecommerceApp.Repository.AddressRepo;
import com.thinkpalm.ecommerceApp.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private AddressRepo addressRepo;
    
    public Map<String, Object> getCustomerDetails() {
        Optional<Customer> cust = customerRepo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Map<String,Object> res = new HashMap<>();
        res.put("name",cust.get().getName());
        res.put("email",cust.get().getEmail());
        res.put("phone_number",cust.get().getPhone_number());
        res.put("role",cust.get().getRole());
        List<Address> address = addressRepo.findByCustId(cust.get().getId());
        for(Address add:address){
            add.setCustomer(null);
        }
        res.put("address",address);
        return res;
    }
    public void deleteAccount(Integer custId) {
        customerRepo.deleteById(custId);
    }
}

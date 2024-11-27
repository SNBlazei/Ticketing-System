package com.example.spring.repository;

import com.example.spring.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface customerRepo extends JpaRepository<Customer,Long> {
    Customer findByCustomerId(Long customerId);
    Customer findByName(String name);
    Customer findByEmail(String email);
    Customer findByPhone(String phone);

}

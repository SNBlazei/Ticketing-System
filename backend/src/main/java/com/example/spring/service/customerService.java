package com.example.spring.service;
import com.example.spring.model.Customer;
import com.example.spring.repository.customerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class customerService {
    @Autowired

    private customerRepo repo;

    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }
    public Customer getCustomerById(long id) {
        return repo.findById(id).orElse(null);
    }
    public Customer saveCustomer(Customer customer) {
        return repo.save(customer);
    }
    public void deleteCustomer(long id) {
        repo.deleteById(id);
    }
    public Customer updateCustomer(long id, Customer customer) {
        return repo.findById(id).orElse(null);
    }
    public Customer getCustomerByEmail(String email) {
        return repo.findByEmail(email);
    }
    public Customer getCustomerByPhone(String phone) {
        return repo.findByPhone(phone);
    }

}

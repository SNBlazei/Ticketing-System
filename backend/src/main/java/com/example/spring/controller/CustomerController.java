package com.example.spring.controller;


import com.example.spring.model.Customer;
import com.example.spring.service.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private customerService service;

    @GetMapping
    public List<Customer> findAll() {
        return service.getAllCustomers();
    }
    @GetMapping("/{id}")
    public Customer findById(@PathVariable  long id) {
        return service.getCustomerById(id);
    }
    @PostMapping
    public Customer add(@RequestBody Customer customer) {
        System.out.println(customer.toString());
        return service.saveCustomer(customer);
    }
}

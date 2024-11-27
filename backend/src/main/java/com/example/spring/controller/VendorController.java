package com.example.spring.controller;


import com.example.spring.model.Vendor;
import com.example.spring.service.vendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {
    @Autowired
    private vendorService service;
    @GetMapping
    public List<Vendor> findAll() {
        return service.getAllVendors();
    }
    @GetMapping("/{id}")
    public Vendor findById(@PathVariable long id) {
        return service.findById(id);
    }
    @PostMapping
    public Vendor add(@RequestBody Vendor vendor) {
        System.out.println(vendor.toString());
        return service.save(vendor);
    }





}

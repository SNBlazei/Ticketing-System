package com.example.spring.service;
import com.example.spring.model.Vendor;
import com.example.spring.repository.TicketRepo;
import com.example.spring.repository.vendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class vendorService {
    @Autowired
    private vendorRepo repo;


    public Vendor findById(long id) {
        return repo.findById(id).orElse(null);

    }
    public List<Vendor> getAllVendors() {
        return repo.findAll();
    }

    public Vendor save(Vendor vendor) {
       return repo.save(vendor);
    }
    public void delete(long id) {
        repo.deleteById(id);

    }
    public Vendor findByName(String name) {
        return repo.findByName(name);
    }
    public Vendor findByEmail(String email) {
        return repo.findByEmail(email);
    }




}

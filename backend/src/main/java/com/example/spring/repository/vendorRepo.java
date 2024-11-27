package com.example.spring.repository;
import com.example.spring.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface vendorRepo extends JpaRepository<Vendor,Long> {
    Vendor findByName(String name);
    Vendor findByEmail(String email);


}

package com.example.spring.repository;

import com.example.spring.model.Booking;
import com.example.spring.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface bookingRepo extends JpaRepository<Booking, Long> {


}

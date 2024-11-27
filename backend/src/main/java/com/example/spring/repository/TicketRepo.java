package com.example.spring.repository;

import com.example.spring.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long> {
    Ticket findByName(String name);
    Ticket findByPrice(int price);
}
package com.example.spring.service;

import com.example.spring.model.Ticket;
import com.example.spring.repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepo ticketRepo;

    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }

    public Ticket findTicketById(long id) {
        return ticketRepo.findById(id).orElse(null);
    }

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepo.save(ticket);
    }

    public void deleteTicket(long id) {
        ticketRepo.deleteById(id);
    }

    public Ticket findTicketByName(String name) {
        return ticketRepo.findByName(name);
    }

    public Ticket findTicketByPrice(int price) {
        return ticketRepo.findByPrice(price);
    }


}

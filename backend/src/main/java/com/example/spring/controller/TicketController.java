package com.example.spring.controller;

import com.example.spring.model.Ticket;
import com.example.spring.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<Ticket> findAll() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public Ticket findById(@PathVariable long id) {
        return ticketService.findTicketById(id);
    }

    @PostMapping
    public Ticket create(@RequestBody Ticket ticket) {
        System.out.println(ticket.toString());
        return ticketService.saveTicket(ticket);
    }

    @PutMapping("/{id}")
    public Ticket update(@PathVariable String id, @RequestBody Ticket ticket) {
        return ticketService.saveTicket(ticket);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        ticketService.deleteTicket(id);
    }
}
package com.example.spring.controller;

import com.example.spring.model.Customer;
import com.example.spring.model.Ticket;
import com.example.spring.model.TicketPool;
import com.example.spring.model.Vendor;
import com.example.spring.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    private TicketPool ticketPool;
    private Ticket ticket;
    private Vendor vendorThread;
    private Customer customerThread;

    @GetMapping
    public List<Ticket> findAll() {
        return ticketService.getAllTickets();
    }

    @PostMapping("/start")
    public String startThread(@RequestParam int maxTicketCapacity) {
        ticketPool = new TicketPool(maxTicketCapacity);

        vendorThread = new Vendor(ticketPool,new Ticket(1l,"Match ticket" ,1000));
        customerThread=new Customer(ticketPool);
        vendorThread.start();
        customerThread.start();

        return "success";

    }

    @PostMapping("/stop")
    public String stopThreads() {
        if (ticketPool != null) {
            ticketPool.stopSystem();
        }
        return "System Stopped";
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
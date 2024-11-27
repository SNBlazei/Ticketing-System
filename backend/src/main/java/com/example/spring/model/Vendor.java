package com.example.spring.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Vendor extends Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String phone;
    private int config;

    @Transient
    private TicketPool ticketPool;
    @OneToOne(cascade = CascadeType.ALL)
    private  Ticket ticket;

    public Vendor(TicketPool ticketPool, Ticket ticket) {
        this.ticketPool = ticketPool;
        this.ticket = ticket;
    }

    @Override
    public void run() {
        try {
            while (ticketPool.isRunning()) {
                ticketPool.addTickets(ticket);
                Thread.sleep(1000);
            }

        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }


}

package com.example.spring.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer extends Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String phone;

    @Transient
    private  TicketPool ticketPool;

    public Customer( TicketPool ticketPool) {

        this.ticketPool = ticketPool;
    }
    @Override
    public void run() {
        try {
            while(ticketPool.isRunning()){
                Ticket ticket=ticketPool.removeTicket();
                if (ticket!=null){
                    System.out.println("Customer purchased " + ticket.toString());
                }
                Thread.sleep(1500);
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}

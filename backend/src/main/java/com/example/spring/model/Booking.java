package com.example.spring.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "ticketId", referencedColumnName = "id")
    private Ticket ticket;
    @ManyToOne
    @JoinColumn(name = "vendorId", referencedColumnName = "id")
    private Vendor vendor;
    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;
    private int bookingType;
    private int dateGiven;
}

package model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

  //  @OneToOne
   // @JoinColumn(name = "customer_id")
  //  private Customer customer;

 //   @ManyToOne
  //  @JoinColumn(name = "vendor_id")
    //private Vendor vendor;

    //private String bookingType;
   // private LocalDateTime dateGiven;


}

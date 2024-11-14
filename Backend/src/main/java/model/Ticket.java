package model;

import jakarta.persistence.*;
import org.springframework.data.repository.cdi.Eager;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;
    private String description;


    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;


    public Ticket() {

    }
    public Ticket(int id,int price, String description, Event event) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.event = event;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }



}

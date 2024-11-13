package model;

import jakarta.persistence.*;

@Entity

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int totalTickets;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    public Event() {

    }
    public Event(String name, String description, int totalTickets, Vendor vendor) {
        this.name = name;
        this.description = description;
        this.totalTickets = totalTickets;

    }
    public int getId() {
        return id;

    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;

    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getTotalTickets() {
        return totalTickets;
    }
    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }
}

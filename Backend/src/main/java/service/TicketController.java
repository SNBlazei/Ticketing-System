package service;

import model.TicketPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.TicketRepository;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketPool ticketPool;
    @Autowired
    public TicketController(TicketPool ticketPool) {
        this.ticketPool = ticketPool;

    }
    @GetMapping("/status")
    public String status() {
        return "Ticket available:" + ticketPool.hasTicket();

    }
    @PostMapping("/stop")
    public String stop() {
        ticketPool.stopSystem();
        return "System stopped";
    }

}

package com.example.spring.model;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import java.util.concurrent.ConcurrentLinkedDeque;

public class TicketPool {
    private final ConcurrentLinkedDeque<Ticket> tickets=new ConcurrentLinkedDeque<>();
    private final int maxTicketCapacity;
    private final Lock lock=new ReentrantLock();
    private boolean running=true;

    public TicketPool(int maxTicketCapacity) {
        this.maxTicketCapacity=maxTicketCapacity;
    }
    public void stopSystem(){
        running=false;
    }
    public boolean isRunning() {
        return running;
    }


    public void addTickets(Ticket ticket) throws InterruptedException {
        lock.lock();
        try {
            if (tickets.size()<maxTicketCapacity) {
                tickets.add(ticket);
                System.out.println("Ticket added: "+ticket.toString());
            }else{
                System.out.println("Ticket exceeded max number of tickets: "+maxTicketCapacity);
            }
        }finally {
            lock.unlock();
        }
    }

    public Ticket removeTicket() throws InterruptedException {
        lock.lock();
        try{
            if(!tickets.isEmpty()){
                Ticket ticket=tickets.poll();
                System.out.println("Ticket removed: "+ticket.toString());
                return ticket;
            }else {
                System.out.println("No tickets to remove");
            }
        }finally {
            lock.unlock();
        }
        return null;
    }



}

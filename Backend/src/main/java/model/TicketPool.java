package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TicketPool {
    private final Queue<Ticket> ticketQueue = new ConcurrentLinkedQueue<>();
    private final int maxCapacity;
    private boolean running = true;
    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTicket(int tickets) throws InterruptedException{
        while (ticketQueue.size() >= maxCapacity) {
            System.out.println("Ticket pool is full.waiting");
            wait();
        }
        for (int i = 0; i < tickets; i++) {
            ticketQueue.add(new Ticket());

        }
        System.out.println("Added " + tickets + " tickets");
        notifyAll();


    }

    public synchronized Ticket removeTicket() throws InterruptedException{
        while (ticketQueue.isEmpty() && running) {
            System.out.println("Ticket pool is empty.waiting");
            wait();
        }
        if(!running) {
            System.out.println("System Stopped");
            return null;

        }
        Ticket ticket = ticketQueue.poll();
        System.out.println("Ticket purchased: " + ticket);
        notifyAll();
        return ticket;


    }

    public synchronized boolean hasTicket(){
        return !ticketQueue.isEmpty();
    }
    public synchronized void stopSystem(){
        running = false;
        notifyAll();
    }
    public synchronized boolean isRunning(){
        return running;
    }




}

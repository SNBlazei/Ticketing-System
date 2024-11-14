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
            wait();
        }
        for (int i = 0; i < tickets; i++) {
            ticketQueue.add(new Ticket());

        }
        notifyAll();


    }

    public synchronized Ticket removeTicket() throws InterruptedException{
        while (ticketQueue.isEmpty()) {
            wait();
        }try {
            wait();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();


        }
        Ticket ticket = ticketQueue.poll();
        notifyAll();
        return ticket;


    }

    public boolean hasTicket(){
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

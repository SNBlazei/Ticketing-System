public class Vendor implements Runnable {

    private final TicketPool TicketPool;
    private final int producingTickets;
    private final int ticketReleaseRate;

    public Vendor(TicketPool TicketPool,int producingTickets,int ticketReleaseRate) {
        this.TicketPool = TicketPool;
        this.producingTickets = producingTickets;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        try {
            while (TicketPool.isRunning()) {
                TicketPool.addTickets(producingTickets);
                Thread.sleep(ticketReleaseRate);

            }

            }
        catch(InterruptedException e){
               System.out.println("Error");



        }


    }

}

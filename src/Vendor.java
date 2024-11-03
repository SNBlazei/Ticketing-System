public class Vendor implements Runnable {

    private final TicketPool ticketPool;
    private final int producingTickets;
    private final int ticketReleaseRate;

    public Vendor(TicketPool ticketPool,int producingTickets,int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.producingTickets = producingTickets;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        try {
            while (ticketPool.isRunning()) {
                System.out.println("Vendor adding" + producingTickets + " tickets");
                ticketPool.addTickets(producingTickets);
                Thread.sleep(ticketReleaseRate);

            }

            }
        catch(InterruptedException e){
               System.out.println("Error");



        }


    }

}

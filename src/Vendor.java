public class Vendor implements Runnable {

    private final TicketingSystem ticketingSystem;
    private final int producingTickets;
    private final int ticketReleaseRate;

    public Vendor(TicketingSystem ticketingSystem,int producingTickets,int ticketReleaseRate) {
        this.ticketingSystem = ticketingSystem;
        this.producingTickets = producingTickets;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        try {
            while (ticketingSystem.isRunning()) {
                ticketingSystem.addTickets(producingTickets);
                Thread.sleep(ticketReleaseRate);

            }

            }
        catch(InterruptedException e){
               System.out.println("Error");



        }


    }

}

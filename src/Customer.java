public class Customer implements Runnable {

    private final TicketingSystem ticketingSystem;
    private final int customerRetrievalRate;
    private final int ticketsToBuy;

    public Customer(TicketingSystem ticketingSystem, int customerRetrievalRate, int ticketsToBuy) {
            this.ticketingSystem = ticketingSystem;
            this.customerRetrievalRate = customerRetrievalRate;
            this.ticketsToBuy = ticketsToBuy;

    }


    @Override
    public void run() {
        while (ticketingSystem.isRunning()) {

            try {
                ticketingSystem.removeTickets(ticketsToBuy);
                Thread.sleep(customerRetrievalRate);
            }catch (InterruptedException e) {
                System.out.println("Error while removing tickets");
            }

        }
    }
}

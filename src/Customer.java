public class Customer implements Runnable {

    private final TicketPool TicketPool;
    private final int customerRetrievalRate;
    private final int ticketsToBuy;

    public Customer(TicketPool TicketPool, int customerRetrievalRate, int ticketsToBuy) {
            this.TicketPool = TicketPool;
            this.customerRetrievalRate = customerRetrievalRate;
            this.ticketsToBuy = ticketsToBuy;

    }



    @Override
    public void run() {
        while (TicketPool.isRunning()) {

            try {
                TicketPool.removeTickets(ticketsToBuy);
                Thread.sleep(customerRetrievalRate);
            }catch (InterruptedException e) {
                System.out.println("Error while removing tickets");
            }

        }
    }
}

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
                System.out.println("Customer purchased " +ticketsToBuy + " tickets");
                Thread.sleep(customerRetrievalRate);
            }catch (InterruptedException e) {
                System.out.println("Interrupted");

            }

        }
    }
}

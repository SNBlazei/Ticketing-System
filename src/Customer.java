public class Customer implements Runnable {

    private final TicketPool ticketPool;
    private final int customerRetrievalRate;
    private final int ticketsToBuy;

    public Customer(TicketPool ticketPool, int customerRetrievalRate, int ticketsToBuy) {
            this.ticketPool = ticketPool;
            this.customerRetrievalRate = customerRetrievalRate;
            this.ticketsToBuy = ticketsToBuy;

    }



    @Override
    public void run() {
        try {
            while (ticketPool.isRunning()) {
                synchronized (ticketPool) {
                    if (ticketPool.getCurrentTickets()>=ticketsToBuy) {
                        ticketPool.removeTickets(ticketsToBuy);
                        System.out.println("Customer purchased" + ticketsToBuy + " tickets");
                        ticketPool.notifyAll();

                    }else {
                        System.out.println("Customer Waiting");
                        ticketPool.wait();
                    }

                }
                Thread.sleep(customerRetrievalRate);

            }
        }catch (Exception e) {
            System.out.println("Interrupted");

        }

    }

}

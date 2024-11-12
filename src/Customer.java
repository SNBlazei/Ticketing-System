public class Customer implements Runnable {
    private int customerID;
    private int retrievalInterval;
    private TicketPool ticketPool;

    public Customer(int customerID, int retrievalInterval,TicketPool ticketPool) {
        this.customerID = customerID;
        this.retrievalInterval = retrievalInterval;
        this.ticketPool = ticketPool;
    }


    @Override
    public void run() {
        while(ticketPool.isRunning()){
            try {
                int ticketsToBuy=1;
                ticketPool.removeTickets(ticketsToBuy);
                System.out.println(" Customer " + customerID + " purchased" + ticketsToBuy + " Ticket ");
                Thread.sleep(retrievalInterval);
            }catch(InterruptedException e){
                System.out.println(" Customer " + customerID + " interrupted ");
            }
        }

    }
}

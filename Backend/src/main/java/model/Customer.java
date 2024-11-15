package model;




public class Customer implements Runnable {

    private final  TicketPool ticketPool;
    private final  int customerId;



    public Customer(TicketPool ticketPool, int customerId) {
        this.ticketPool = ticketPool;
        this.customerId = customerId;

    }

    @Override
    public void run() {

        try {
            while(ticketPool.isRunning()){
                if (ticketPool.hasTicket()){
                    Ticket ticket=ticketPool.removeTicket();
                    System.out.println("Customer "+customerId+" purchased "+ticket);

                }else{
                    System.out.println("Customer "+customerId+" is waiting");
                }
                Thread.sleep(1000);
            }
        }catch (Exception e){
            System.out.println("Customer "+customerId+" Interrupted");
            Thread.currentThread().interrupt();

        }


    }


}

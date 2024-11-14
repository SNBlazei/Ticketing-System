package model;





public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int vendorId;

    public Vendor(TicketPool ticketPool, int vendorId) {
        this.ticketPool = ticketPool;
        this.vendorId = vendorId;
    }









    @Override
    public void run() {
        try {
            while(ticketPool.isRunning()){
                ticketPool.addTicket(10);
                System.out.println("Vendor " + vendorId + " added 10 Tickets");
                Thread.sleep(1000);

            }
        }catch(Exception e){
            Thread.currentThread().interrupt();

        }


    }

}

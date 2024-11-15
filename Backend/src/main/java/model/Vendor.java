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
                ticketPool.addTicket(5);
                System.out.println("Vendor " + vendorId + " added  Tickets");
                Thread.sleep(1000);

            }
        }catch(Exception e){
            System.out.println("Vendor "+vendorId+" failed to add Tickets");
            Thread.currentThread().interrupt();

        }


    }

}

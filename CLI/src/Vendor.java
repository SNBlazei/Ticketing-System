public  class Vendor implements Runnable {

    private int vendorId;
    private int ticketPerRelease;
    private int releaseInterval;
    private TicketPool ticketPool;

    public Vendor(int vendorId, int ticketPerRelease, int releaseInterval,TicketPool ticketPool) {

        this.vendorId = vendorId;
        this.ticketPerRelease = ticketPerRelease;
        this.releaseInterval = releaseInterval;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        while (ticketPool.isRunning()){
            try {
                ticketPool.addTickets(ticketPerRelease);
                System.out.println(" Vendor " + vendorId + " added " + ticketPerRelease + " Tickets ");
                Thread.sleep(releaseInterval);
            }catch (InterruptedException e){
                System.out.println(" Vendor " + vendorId + " interrupted ");

            }
        }

    }

}
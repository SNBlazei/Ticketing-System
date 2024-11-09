public class Vendor implements Runnable {

    private final TicketPool ticketPool;
    private final int ticketsToAdd;
    private final int ticketReleaseRate;
    private final int vendorId;

    public Vendor(TicketPool ticketPool,int ticketsToAdd,int ticketReleaseRate,int vendorId) {
        this.ticketPool = ticketPool;
        this.ticketsToAdd = ticketsToAdd;
        this.ticketReleaseRate = ticketReleaseRate;
        this.vendorId = vendorId;
    }

    @Override
    public void run() {
        try {
            while (ticketPool.isRunning()) {
                synchronized (ticketPool) {
                    if (ticketPool.getCurrentTickets() < ticketPool.getMaxTicketCapacity()) {
                        ticketPool.addTickets(1);
                        System.out.println("Vendor " + vendorId + " tickets");
                        ticketPool.notifyAll();

                    } else {
                        System.out.println("Vendor " + vendorId + " cannot add more tickets ");
                        ticketPool.wait();
                    }

                }
                Thread.sleep(ticketReleaseRate);


            }

        } catch (InterruptedException e) {
            System.out.println("Vendor " + vendorId + " interrupted");
        }






        }


    }



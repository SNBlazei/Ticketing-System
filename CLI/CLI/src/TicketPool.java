import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;


public class TicketPool {


    private  int totalTickets;
    private  int ticketReleaseRate;
    private  int customerRetrievalRate;
    private  int maxTicketCapacity;
    private  int currentTickets;
    private boolean running=false;

    public TicketPool(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity,int currentTickets) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.currentTickets = currentTickets;

    }

    public int getTotalTickets() {
        return totalTickets;
    }
    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public synchronized int getCurrentTickets() {

        return currentTickets;
    }
    public  boolean isRunning() {
        return running;
    }



    public void  configure(Scanner scanner) {



        while (true){
            System.out.println("Enter the total number of tickets (1-1000)");
            if(scanner.hasNextInt()) {
                totalTickets = scanner.nextInt();

                if (totalTickets > 0 && totalTickets <1000) {

                    break;
                } else {
                    System.out.println("value must be between 1 and 1000");
                }
            }else{
                System.out.println("Invalid input");
                scanner.next();
            }
        }


        while (true){
            System.out.println("Enter the ticket release rate (1-10)");
            if(scanner.hasNextInt()) {
                ticketReleaseRate = scanner.nextInt();

                if (ticketReleaseRate > 0 && ticketReleaseRate <= 10) {
                    break;
                }else {
                    System.out.println("value must be between 1 and 10");
                }
            }else {
                System.out.println("Invalid input");
                scanner.next();
            }
        }

        while (true){
            System.out.println("Enter the customer retrieval rate (1-30)");
            if(scanner.hasNextInt()) {
                customerRetrievalRate = scanner.nextInt();

                if (customerRetrievalRate >= 1 && customerRetrievalRate <= 30) {
                    break;
                }else {
                    System.out.println("value must be between 400 and 1500");
                }
            }else {
                System.out.println("Invalid input");
                scanner.next();
            }
        }

        while (true){
            System.out.println("Enter the max ticket capacity (100-5000)");
            if(scanner.hasNextInt()) {
                maxTicketCapacity = scanner.nextInt();
                if (maxTicketCapacity >= 100 && maxTicketCapacity <= 5000) {
                    break;
                }else{
                    System.out.println("value must be between 100 and 5000");
                }
            }else {
                System.out.println("Invalid input");
                scanner.next();
            }
        }

        System.out.println("Total number of tickets: " + totalTickets);
        System.out.println("Ticket release rate: " + ticketReleaseRate);
        System.out.println("Customer retrieval: " + customerRetrievalRate);
        System.out.println("Max ticket capacity: " + maxTicketCapacity);

    }

    public void startSystem(){
        if(running){
            System.out.println("System is already running");
            return;
        }
        currentTickets=totalTickets;
        running=true;
        System.out.println("System started");

    }

    public void stopSystem(){
        if(!running){
            System.out.println("System is already stopped");
            return;
        }
        running=false;
        currentTickets =totalTickets;
        System.out.println("System stopped");
    }

    public synchronized void addTickets(int vendorId, int tickets) throws InterruptedException {
        while (currentTickets + tickets > maxTicketCapacity) {
            System.out.println("Maximum number of tickets reached. Vendor " + vendorId + " is waiting...");
            wait(); // Wait until tickets are consumed
        }

        // Add tickets if there is enough capacity
        currentTickets += tickets;
        logTransactions("Vendor " + vendorId + " added " + tickets + " tickets. Current Tickets: " + currentTickets);
        System.out.println("Vendor " + vendorId + " added " + tickets + " Tickets. Current Tickets: " + currentTickets);

        notifyAll(); // Notify customers and other threads
    }


    public synchronized void removeTickets(int customerID, int tickets) throws InterruptedException {
        while (currentTickets - tickets < 0) {
            System.out.println("No tickets available. Customer " + customerID + " is waiting...");
            wait(); // Wait until tickets are added
        }

        // Remove tickets if there are enough
        currentTickets -= tickets;
        logTransactions("Customer " + customerID + " purchased " + tickets + " ticket(s). Current Tickets: " + currentTickets);
        System.out.println("Customer " + customerID + " purchased " + tickets + " Ticket(s). Current Tickets: " + currentTickets);

        notifyAll(); // Notify vendors and other threads
    }

    public synchronized void showCurrentTickets(){
        System.out.println("Current tickets: " + getCurrentTickets());

    }
    private void logTransactions(String massage){
        logger.info("LOG:" + massage + " |currentTickets: "+ currentTickets);
    }
    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());
    static {
        try {
            FileHandler fileHandler=new FileHandler("Ticketing.log",true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        }catch (IOException e){
            System.out.println("Error while creating log file");

        }
    }


}

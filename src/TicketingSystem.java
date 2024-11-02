import java.util.Scanner;

public class TicketingSystem {
    private  int totalTickets;
    private  int ticketReleaseRate;
    private  int customerRetrievalRate;
    private  int maxTicketCapacity;
    private boolean running=false;

    public TicketingSystem(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;

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

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }
    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }
    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }
    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public void  configure(Scanner scanner) {



        while (true){
            System.out.println("Enter the total number of tickets");
            if(scanner.hasNextInt()) {
                totalTickets = scanner.nextInt();

                if (totalTickets > 0) {

                    break;
                } else {
                    System.out.println("value must be positive integer");
                }
            }else{
                System.out.println("Invalid input");
                scanner.next();
            }
        }


        while (true){
            System.out.println("Enter the ticket release rate");
            if(scanner.hasNextInt()) {
                ticketReleaseRate = scanner.nextInt();

                if (ticketReleaseRate > 0) {
                    break;
                }else {
                    System.out.println("value must be positive integer");
                }
            }else {
                System.out.println("Invalid input");
                scanner.next();
            }
        }

        while (true){
            System.out.println("Enter the customer retrieval");
            if(scanner.hasNextInt()) {
                customerRetrievalRate = scanner.nextInt();

                if (customerRetrievalRate > 0) {
                    break;
                }else {
                    System.out.println("value must be positive integer");
                }
            }else {
                System.out.println("Invalid input");
                scanner.next();
            }
        }

        while (true){
            System.out.println("Enter the max ticket capacity");
            if(scanner.hasNextInt()) {
                maxTicketCapacity = scanner.nextInt();
                if (maxTicketCapacity > 0) {
                    break;
                }else{
                    System.out.println("value must be positive integer");
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
        running=true;
        System.out.println("System started");
    }

    public void stopSystem(){
        if(!running){
            System.out.println("System is already stopped");
            return;
        }
        running=false;
        System.out.println("System stopped");
    }

}

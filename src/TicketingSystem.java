import java.util.Scanner;

public class TicketingSystem {
    private  int totalTickets;
    private  int ticketReleaseRate;
    private  int customerRetrievalRate;
    private  int maxTicketCapacity;

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

    public void  configure(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the total number of tickets");
        int total=scanner.nextInt();
        System.out.println("Enter the ticket release rate");
        int rate=scanner.nextInt();
        System.out.println("Enter the customer retrieval rate");
        int rate1=scanner.nextInt();
        System.out.println("Enter the max capacity");
        int capacity=scanner.nextInt();
    }
}

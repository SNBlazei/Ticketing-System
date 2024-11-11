import java.io.Serializable;
import java.io.FileWriter;
import java.io.IOException;

public class Configuration implements Serializable {

    private static final long serialVersionUID = 1L;

    private int totalTickets;
    private int ticketReleaseRate;
    private  int customerRetrievalRate;
    private  int maxTicketCapacity;

    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;



    }




    public void saveFile(String fileName) {
        try(FileWriter fileWriter=new FileWriter(fileName)) {
            fileWriter.write("Total Tickets: " + totalTickets);
            fileWriter.write("\nTicket Release Rate: " + ticketReleaseRate);
            fileWriter.write("\nCustomer Retrieval Rate: " + customerRetrievalRate);
            fileWriter.write("\nMax Ticket Capacity: " + maxTicketCapacity);
            System.out.println("Saved to " + fileName);


        }catch (IOException e) {
            System.out.println("Error while saving file");
            e.printStackTrace();
        }

    }
}

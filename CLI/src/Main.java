import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        TicketPool ticketPool=new TicketPool(0,0,0,0,0);
        ticketPool.configure(scanner);
        Configuration configuration=new Configuration(
                ticketPool.getTotalTickets(),
                ticketPool.getTicketReleaseRate(),
                ticketPool.getCustomerRetrievalRate(),
                ticketPool.getMaxTicketCapacity()

        );




        System.out.println("Enter the file name (.txt)");
        String fileName = scanner.next();
        configuration.saveFile(fileName);









        while (true){
            System.out.println("Enter order (1:Start,2:Stop):");

            if(scanner.hasNextInt()) {
                int order = scanner.nextInt();
                scanner.nextLine();

                switch (order) {
                    case 1:
                        ticketPool.startSystem();
                        List<Thread> threads=new ArrayList<>();
                        Thread vendorThread1=new Thread(new Vendor(1,20,2000, ticketPool));
                        Thread vendorThread2=new Thread(new Vendor(2,20,1500, ticketPool));
                        threads.add(vendorThread1);
                        threads.add(vendorThread2);


                        Thread customerThread1=new Thread(new Customer(1,1500,ticketPool));
                        Thread customerThread2=new Thread(new Customer(2,2000,ticketPool));
                        threads.add(customerThread1);
                        threads.add(customerThread2);

                        for (Thread thread : threads) {
                            thread.start();
                        }






                        break;
                    case 2:
                        ticketPool.stopSystem();
                        System.out.println("System Closed");
                        scanner.close();
                        return;


                    default:
                        System.out.println("Invalid order");


                }

            }else{
                System.out.println("Invalid input");
                scanner.nextLine();
            }

        }








    }


}



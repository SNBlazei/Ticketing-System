import java.util.Scanner;

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


        System.out.println("Do you want to save? (y/N)");
        String answer = scanner.next();
        if (answer.equalsIgnoreCase("yes")) {
            System.out.println("Enter the file name (.txt)");
            String filename = scanner.next();
            configuration.saveFile(filename);
        } else if (answer.equalsIgnoreCase("N")) {
            System.out.println("Not saved");


        }else {
            System.out.println("Invalid choice");
        }








        while (true){
            System.out.println("Enter order (1:Start,2:Stop):");

            if(scanner.hasNextInt()) {
                int order = scanner.nextInt();
                scanner.nextLine();

                switch (order) {
                    case 1:
                        ticketPool.startSystem();
                        Thread vendorThread=new Thread(new Vendor(1,3,2000, ticketPool));
                        Thread customerThread=new Thread(new Customer(1,1500,ticketPool));

                        vendorThread.start();
                        customerThread.start();







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
    private static void ticketOperations(Scanner scanner, TicketPool TicketPool) {

        while(TicketPool.isRunning()){
            System.out.println("1:Add ticket , 2:Remove ticket , 3:Show Current Tickets , 4:Stop");

            if(scanner.hasNextInt()) {
                int operation = scanner.nextInt();
            try {
                if(operation == 1) {
                    System.out.println("Enter the amount of tickets to add");
                    int amount = scanner.nextInt();
                    TicketPool.addTickets(amount);
                    break;
                }else if(operation == 2) {
                    System.out.println("Enter the amount of tickets to remove");
                    int amountToRemove = scanner.nextInt();
                    TicketPool.removeTickets(amountToRemove);
                    break;


                }else if(operation == 3) {
                    TicketPool.showCurrentTickets();
                    break;
                }else if(operation == 4) {
                    TicketPool.stopSystem();
                    break;
                }

            }catch (InterruptedException e) {
                System.out.println("Error");
            }


            }else{
                System.out.println("Invalid input");
                scanner.nextLine();
            }



            }
        }

    }



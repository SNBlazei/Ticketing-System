import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        TicketPool TicketPool=new TicketPool(0,0,0,0,0);
        TicketPool.configure(scanner);
        Configuration configuration=new Configuration(
                TicketPool.getTotalTickets(),
                TicketPool.getTicketReleaseRate(),
                TicketPool.getCustomerRetrievalRate(),
                TicketPool.getMaxTicketCapacity()

        );


        System.out.println("Do you want to save? (y/N)");

        String answer = scanner.next();
        if (answer.equalsIgnoreCase("yes")) {
            System.out.println("Enter the file name (.txt)");
            String filename = scanner.next();
            configuration.saveFile(filename);
        } else if (answer.equalsIgnoreCase("no")) {
            System.out.println("Not saved");


        }else {
            System.out.println("Invalid choice.Enter (yes or no)");


        }




        System.out.println("Enter the number of vendors");
        int vendors = scanner.nextInt();
        System.out.println("Enter the number of customers");
        int customers = scanner.nextInt();

        Thread [] vendorThreads=new Thread[vendors];
        Thread [] customerThreads=new Thread[customers];




        while (true){
            System.out.println("Enter order (1:Start,2:Stop,3:Exit):");

            if(scanner.hasNextInt()) {
                int order = scanner.nextInt();
                scanner.nextLine();

                switch (order) {
                    case 1:
                        TicketPool.startSystem();
                        for (int i = 0; i < vendors; i++) {
                            Vendor vendor=new Vendor(TicketPool,TicketPool.getTotalTickets(),TicketPool.getTicketReleaseRate(),i);
                            vendorThreads[i]=new Thread(vendor);
                            vendorThreads[i].start();

                        }
                        for (int i = 0; i < customers; i++) {
                            Customer customer=new Customer(TicketPool,TicketPool.getCustomerRetrievalRate(),1);
                            customerThreads[i]=new Thread(customer);
                            customerThreads[i].start();
                        }
                        try {
                            Thread.sleep(2000);
                        }catch (InterruptedException e){
                            System.out.println("Error");
                        }




                        ticketOperations(scanner, TicketPool);

                        break;
                    case 2:
                        TicketPool.stopSystem();
                        for(Thread venndorThread:vendorThreads) {
                            if(venndorThread !=null) {
                                venndorThread.interrupt();

                            }
                        }
                        for (Thread customerThread:customerThreads) {
                            if(customerThread !=null) {
                                customerThread.interrupt();
                            }
                        }
                        break;
                    case 3:
                        TicketPool.stopSystem();
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



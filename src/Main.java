import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int vendors=3;
        int customers=6;
        Scanner scanner = new Scanner(System.in);
        TicketPool TicketPool=new TicketPool(0,0,0,0,0);



        TicketPool.configure(scanner);


        Vendor vendor=new Vendor(TicketPool,0,0);
        Thread t1=new Thread(vendor);
        Customer customer=new Customer(TicketPool,0,0);
        Thread t2=new Thread(customer);
        t1.start();
        t2.start();


        while (true){
            System.out.println("Enter order (1:Start,2:Stop,3:Exit):");

            if(scanner.hasNextInt()) {
                int order = scanner.nextInt();
                scanner.nextLine();

                switch (order) {
                    case 1:
                        TicketPool.startSystem();
                        ticketOperations(scanner, TicketPool);

                        break;
                    case 2:
                        TicketPool.stopSystem();
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



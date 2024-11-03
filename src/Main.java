import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketingSystem ticketingSystem=new TicketingSystem(0,0,0,0,0);



        ticketingSystem.configure(scanner);

        Vendor vendor=new Vendor(ticketingSystem,0,0);
        Thread t1=new Thread(vendor);
        Customer customer=new Customer(ticketingSystem,0,0);
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
                        ticketingSystem.startSystem();
                        ticketOperations(scanner, ticketingSystem);

                        break;
                    case 2:
                        ticketingSystem.stopSystem();
                        break;
                    case 3:
                        ticketingSystem.stopSystem();
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
    private static void ticketOperations(Scanner scanner, TicketingSystem ticketingSystem) {

        while(ticketingSystem.isRunning()){
            System.out.println("1:Add ticket , 2:Remove ticket , 3:Show Current Tickets , 4:Stop");

            if(scanner.hasNextInt()) {
                int operation = scanner.nextInt();
            try {
                if(operation == 1) {
                    System.out.println("Enter the amount of tickets to add");
                    int amount = scanner.nextInt();
                    ticketingSystem.addTickets(amount);
                    break;
                }else if(operation == 2) {
                    System.out.println("Enter the amount of tickets to remove");
                    int amountToRemove = scanner.nextInt();
                    ticketingSystem.removeTickets(amountToRemove);
                    break;


                }else if(operation == 3) {
                    ticketingSystem.showCurrentTickets();
                    break;
                }else if(operation == 4) {
                    ticketingSystem.stopSystem();
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



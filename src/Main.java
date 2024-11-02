import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketingSystem ticketingSystem=new TicketingSystem(0,0,0,0,0);


        ticketingSystem.configure(scanner);

        while (true){
            System.out.println("Enter order (1:Start,2:Stop)");

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

                if(operation == 1) {
                    System.out.println("Enter the amount of tickets to add");
                    int amount = scanner.nextInt();
                    ticketingSystem.addTickets(amount);
                    continue;
                }else if(operation == 2) {
                    System.out.println("Enter the amount of tickets to remove");
                    int amountToRemove = scanner.nextInt();
                    ticketingSystem.removeTickets(amountToRemove);
                    continue;


                }else if(operation == 3) {
                    ticketingSystem.showCurrentTickets();
                    continue;
                }else if(operation == 4) {
                    ticketingSystem.stopSystem();
                    break;
                }

            }else{
                System.out.println("Invalid input");
                scanner.nextLine();
            }



            }
        }

    }



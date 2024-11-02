import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketingSystem ticketingSystem=new TicketingSystem(0,0,0,0);


        ticketingSystem.configure();

        while (true){
            System.out.println("Enter order (1:Start,2:Stop)");

            if(scanner.hasNextInt()) {
                int order = scanner.nextInt();
                scanner.nextLine();

                switch (order) {
                    case 1:
                        ticketingSystem.startSystem();
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


}
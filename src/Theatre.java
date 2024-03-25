import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Theatre {
    public static void main(String[] args) {
        System.out.println("\n========================Welcome to the New Theatre=========================\n");
        System.out.println("----------------------------Instructions-----------------------------------\n");
        System.out.println("   || If you want to work with previous data, first\n      restore all the data from saved file using option 6.\n" +
                "   || If you want to restore all the data from the saved\n      file use option 6 before you save current data.\n" +
                "   || After you saved the details, previous data replace\n      with current data.\n" +
                "   || Before you quit from the program make sure to save\n      details using option 5 if you want.\n" +
                "   || You should enter a valid email address when program ask it\n      Ex : vinulaka@gmail.com\n" +
                "\n---------------------Theatre rows and seats details---------------------\n" +
                "\nTheatre has 3 rows\n" +
                "\n   || Row 1 : 12 seats\n" +
                "   || Row 2 : 16 seats\n" +
                "   || Row 3 : 20 seats\n");
        ArrayList<Ticket> ticketList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        boolean run = true;

        int[] row_num1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] row_num2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] row_num3 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        while (run) {
            // Print the main menu
            System.out.println("------------------------------------------------------------------------\n");
            System.out.println("Please select an option :\n ");
            System.out.println("1) Buy a ticket \n");
            System.out.println("2) Print seating area\n");
            System.out.println("3) Cancel ticket\n");
            System.out.println("4) List available seats\n");
            System.out.println("5) Save to file\n");
            System.out.println("6) Load from file\n");
            System.out.println("7) Print ticket information and total price\n");
            System.out.println("8) Sort tickets by price\n");
            System.out.println("    0) Quit\n");
            System.out.println("------------------------------------------------------------------------\n");

            // Prompt the user to input a menu option
            System.out.print("Enter option : ");
            int option;
            try {
                option = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input");
                scanner.nextLine();
                continue;
            }
            switch (option) {
                case 0:
                    run = false;
                    //Exit
                    System.out.println("\nHave a nice day!");
                    break;
                case 1:
                    //Buy
                    buy_ticket(row_num1, row_num2, row_num3,ticketList);
                    break;
                case 2:
                    //Display seats
                    print_seat_area(row_num1, row_num2, row_num3);
                    break;
                case 3:
                    //Cancel
                    cancel_ticket(row_num1, row_num2, row_num3,ticketList);
                    break;
                case 4:
                    //Show available seats
                    show_available(row_num1, row_num2, row_num3);
                    break;
                case 5:
                    //saves the 3 arrays with the row’s information in a file
                    save(row_num1,row_num2,row_num3);
                    break;
                case 6:
                    //loads the file saved in Task 7 and restores the 3 arrays with the row’s information.
                    load (row_num1,row_num2,row_num3);
                    break;
                case 7:
                    //Print ticket information and ticket price
                    show_tickets_info(ticketList);
                    break;
                case 8:
                    //sort
                    sort_tickets(ticketList);
                    break;
                default:
                    //Other
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    private static void buy_ticket(int[] row_num1, int[] row_num2, int[] row_num3,ArrayList<Ticket> ticketList) {
        try {
            Scanner scanner1 = new Scanner(System.in);
            String name;
            while(true) {
                System.out.print("Enter your name: ");
                name = scanner1.nextLine();
                if (name.matches("[a-zA-Z]+")) {
                    break;
                } else {
                    System.out.println("enter valid string");
                }
            }
            String surname;
            while(true) {
                System.out.print("Enter your surname: ");
                surname = scanner1.nextLine();
                if (surname.matches("[a-zA-Z]+")){
                    break;
                } else {
                    System.out.println("enter valid string");
                }
            }

            String email;
            while (true) {
                System.out.print("Enter your Email: ");
                email = scanner1.nextLine();
                if (email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    break;
                } else {
                    System.out.println("Invalid email format. Please enter a valid email address.");
                }
            }

            System.out.print("Enter your row number : ");   //Getting user's desired row number as an input
            int row_input = scanner1.nextInt();
            System.out.print("Enter your seat number : ");  //Getting user's desired seat number as an input
            int seat_input = scanner1.nextInt();
            System.out.print("Enter your price : £");   //Get the price
            double price = scanner1.nextInt();
            Person person = new Person(name,surname,email);
            Ticket ticket = new Ticket(row_input,seat_input,price,person);
            seat_input--;
            //Validation
            if (row_input < 1 || row_input > 3) {
                System.out.println("Invalid row number.");
            } else if (seat_input < 0 || seat_input > 19) {
                System.out.println("Invalid seat number.");
            } else if (row_input == 1 && row_num1[seat_input] == 1) {
                System.out.println("This seat is already reserved.");
            } else if (row_input == 2 && row_num2[seat_input] == 1) {
                System.out.println("This seat is already reserved.");
            } else if (row_input == 3 && row_num3[seat_input] == 1) {
                System.out.println("This seat is already reserved.");
            } else {
                if (row_input == 1) {
                    row_num1[seat_input] = 1;
                    ticketList.add(ticket);
                    ticket.print();
                } else if (row_input == 2) {
                    row_num2[seat_input] = 1;
                    ticketList.add(ticket);
                    ticket.print();
                } else {
                    row_num3[seat_input] = 1;
                    ticketList.add(ticket);
                    ticket.print();
                }
                System.out.println("Reservation successful!");
            }
        }catch (Exception e){
            System.out.println("Enter valid integer");
        }
    }

    private static void print_seat_area(int[] row_num1, int[] row_num2, int[] row_num3) {
        //Create new arrays
        String[] row_1 = {"O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O"};
        String[] row_2 = {"O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O"};
        String[] row_3 = {"O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O"};
        //Print stage
        System.out.println("\n                 ***********");
        System.out.println("                 *  STAGE  *");
        System.out.println("                 ***********\n");

        for (int i = 0; i < row_1.length; i++) {        //First row
            if (i == 0) {
                System.out.print("                ");
            }
            if (i == 6) {
                System.out.print(" ");
            }
            if (row_num1[i] == 1) {
                row_1[i] = "X";
            }
            System.out.print(row_1[i]);
        }
        System.out.println(" ");
        for (int i = 0; i < row_2.length; i++) {        //Second row
            if (i == 0) {
                System.out.print("              ");
            }
            if (i == 8) {
                System.out.print(" ");
            }
            if (row_num2[i] == 1) {
                row_2[i] = "X";
            }
            System.out.print(row_2[i]);
        }
        System.out.println(" ");
        for (int i = 0; i < row_3.length; i++) {        //Third row
            if (i == 0) {
                System.out.print("            ");
            }
            if (i == 10) {
                System.out.print(" ");
            }
            if (row_num3[i] == 1) {
                row_3[i] = "X";
            }
            System.out.print(row_3[i]);
        }
        System.out.println(" ");
        System.out.println(" ");
    }

    private static void cancel_ticket(int[] row_num1, int[] row_num2, int[] row_num3,ArrayList<Ticket> ticketList) {
        try {
            ////Getting user input for cancel the ticket
            Scanner scanner1 = new Scanner(System.in);
            System.out.print("Enter your row number : ");
            int row = scanner1.nextInt();
            System.out.print("Enter your seat number : ");
            int seat = scanner1.nextInt();
            seat--;

            if (row < 1 || row > 3) {
                System.out.println("Invalid row number.");
            } else if (seat < 0 || seat > 19) {
                System.out.println("Invalid seat number.");
            } else if (row == 1 && row_num1[seat] == 0) {
                System.out.println("This seat isn't  reserved.");
            } else if (row == 2 && row_num2[seat] == 0) {
                System.out.println("This seat is isn't reserved.");
            } else if (row == 3 && row_num3[seat] == 0) {
                System.out.println("This seat isn't reserved.");
            } else {
                if (row == 1) {
                    row_num1[seat] = 0;
                } else if (row == 2) {
                    row_num2[seat] = 0;
                } else {
                    row_num3[seat] = 0;
                }
                for (Ticket ticket : ticketList){
                    if (ticket.getRow() == row && ticket.getSeat() == seat+1){
                        ticketList.remove(ticket);
                        break;
                    }
                }
                System.out.println("Seat cancel successfully!");
            }
        }catch (Exception e){
            System.out.println("Enter valid integer");
        }

    }

    private static void show_available(int[] row_num1, int[] row_num2, int[] row_num3) {
        System.out.print("\nSeats available in row 1: ");       //Print available seats in row 1
        for (int i = 0; i < row_num1.length; i++) {

            if (row_num1[i] == 0) {
                System.out.print(i + 1 + "  ");
            }
        }
        System.out.println(" ");
        System.out.print("\nSeats available in row 2: ");   //Print available seats in row 2
        for (int a = 0; a < row_num2.length; a++) {

            if (row_num2[a] == 0) {
                System.out.print(a + 1 + "  ");
            }
        }
        System.out.println(" ");
        System.out.print("\nSeats available in row 3: ");   //Print available seats in row 3
        for (int b = 0; b < row_num3.length; b++) {

            if (row_num3[b] == 0) {
                System.out.print(b + 1 + "  ");
            }
        }
        System.out.println(" ");
    }

    private static void save(int[] row_num1, int[] row_num2, int[] row_num3) {

            try{
                //Creating a file
                File file= new File("text.txt");
                boolean file_created = file.createNewFile();
                if (file_created){
                    System.out.println("File successfully created");
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
            try {
                //Write the text file
                FileWriter myWriter = new FileWriter("text.txt");

                for (int i = 0; i < row_num1.length; i++) {
                    myWriter.write(row_num1[i] + " ");
                }
                myWriter.write("\n");

                for (int i = 0; i < row_num2.length; i++) {
                    myWriter.write(row_num2[i] + " ");
                }
                myWriter.write("\n");

                for (int i = 0; i < row_num3.length; i++) {
                    myWriter.write(row_num3[i] + " ");
                }
                myWriter.close();
            }
            catch (Exception e){
                System.out.println("An error occurred.");
            }
        }
    private static void load(int[] row_num1,int[] row_num2,int[] row_num3) {
            try {
                File file= new File("text.txt");
                Scanner reader= new Scanner(file);
                //Read the file
                for (int i=0;i<row_num1.length;i++){
                    int num = reader.nextInt();
                    row_num1[i]=num;
                }
                for (int i=0;i<row_num2.length;i++){
                    int num = reader.nextInt();
                    row_num2[i]=num;
                }
                for (int i=0;i<row_num3.length;i++){
                    int num = reader.nextInt();
                    row_num3[i]=num;
                }
                reader.close();
            }
            catch (IOException e) {
                System.out.println("Error while reading a file.");
                e.printStackTrace();
            }
        }
    private static void show_tickets_info(ArrayList<Ticket> ticketList) {
        double total = 0.0;

        for (Ticket ticket : ticketList) {
            System.out.println("\nName: " + ticket.getPerson().getName() + " " + ticket.getPerson().getSurname());
            System.out.println("Email: " + ticket.getPerson().getEmail());
            System.out.println("Row: " + ticket.getRow() + ",Seat:" + ticket.getSeat());
            System.out.println("Price: £" + ticket.getPrice());
            total += ticket.getPrice();
        }
        System.out.println("Total price of all tickets: £" + total);
    }
    private static void sort_tickets(ArrayList<Ticket> ticketList){
        ArrayList<Ticket> sorted_list = new ArrayList<>(ticketList);
        int n = sorted_list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sorted_list.get(j).getPrice() > sorted_list.get(j + 1).getPrice()) {
                    Ticket temp = sorted_list.get(j);
                    sorted_list.set(j, sorted_list.get(j + 1));
                    sorted_list.set(j + 1, temp);
                }
            }
        }
        double total = 0.0;

        for (Ticket ticket : sorted_list) {
            System.out.println("\nName: " + ticket.getPerson().getName() + " " + ticket.getPerson().getSurname());
            System.out.println("Email: " + ticket.getPerson().getEmail());
            System.out.println("Row: " + ticket.getRow() + ", Seat:" + ticket.getSeat());
            System.out.println("Price: £" + ticket.getPrice());
            total += ticket.getPrice();
        }
        System.out.println("Total price of all tickets: £" + total);
        }
    }







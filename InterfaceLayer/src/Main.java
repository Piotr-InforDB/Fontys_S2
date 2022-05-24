import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static Environment environment = new Environment(new UsersData());
    public static EventsContainer eventsContainer = new EventsContainer(new EventsData(), new TicketTypesData());

    public static void main(String[] args) throws IOException, ParseException {

        System.out.println("");
        System.out.println("Select action:");
        System.out.println("1. Buy ticket");
        System.out.println("2. Login");
        System.out.print(">");

        String sOption = Helpers.readLine();

        if(!Helpers.isInt(sOption)){
            System.out.println("Invalid option");
            Main.main(args);
        }

        int option = Integer.parseInt(sOption);

        switch (option){
            case 1 -> System.out.println("Selected option: Buy ticket");
            case 2 -> Main.login();
            default -> Main.main(args);
        }

    }

    public static void login() throws IOException, ParseException {
        System.out.println("");
        System.out.println("Enter your email");
        System.out.print(">");

        String email = Helpers.readLine();

        System.out.println("Enter your email");
        System.out.print(">");

        String pass = Helpers.readLine();

        User user = environment.login(email, pass);

        if(user.getEmail().equals("failed_user")){
            System.out.println("Invalid credentials");
            Main.login();
        }

        System.out.println("Welcome "+user.getName());
        Main.home(user);
    }

    public static void home(User user) throws IOException, ParseException {
        System.out.println("");
        System.out.println("Select action:");
        System.out.println("1. Create new event");
        System.out.println("2. Create new ticket type");
        System.out.print(">");

        String sOption = Helpers.readLine();

        if(!Helpers.isInt(sOption)){
            System.out.println("Invalid option");
            Main.home(user);
        }

        int option = Integer.parseInt(sOption);

        if(option == 1){
            Event event = eventsContainer.createEvent();
            Boolean save = eventsContainer.storeEvent(event);
            if(save){
                System.out.println("Event " + event.getName() + " successfully save");
            }
            else{
                System.out.println("Something went wrong, try again");
            }

            Main.home(user);
        }
        if(option == 2){
            System.out.println("Select event");

            int index = 0;
            for(Event event : eventsContainer.events){
                System.out.println((index + 1) + ". " + event.getName());
            }

            sOption = Helpers.readLine();
            if(!Helpers.isInt(sOption)){
                System.out.println("Invalid option");
                Main.home(user);
            }

            option = Integer.parseInt(sOption);




            Main.home(user);
        }
        else{
            System.out.println("Invalid option");
            Main.home(user);
        }
    }

}

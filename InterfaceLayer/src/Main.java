import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class Main {

    public static Environment environment = new Environment(new UsersData());
    public static EventsContainer eventsContainer = new EventsContainer(new EventsData(), new TicketTypesData());

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("");
        System.out.println("Select action:");
        System.out.println("1. Buy ticket");
        System.out.println("2. Login");

        ArrayList<String> options = new ArrayList<String>();
        options.add("1");
        options.add("2");

        String option = Helpers.readOption(options);

        switch (Integer.parseInt(option)){
            case 1 -> Main.buyTicket();
            case 2 -> Main.login();
            default -> Main.main(args);
        }

    }

    public static void buyTicket() throws IOException {
        System.out.println();
        System.out.println("Select an event");

        System.out.println("Select event");

        int index = 1;
        ArrayList<String> eventOptions = new ArrayList<>();
        for (Event event : eventsContainer.events) {
            System.out.println(index + ". " + event.getName());
            eventOptions.add(Integer.toString(index));
            index++;
        }

        String select = Helpers.readOption(eventOptions);
        Event event = eventsContainer.events.get(Integer.parseInt(select) - 1);

        System.out.println(event.getName());

    }

    public static void login() throws IOException, ParseException {
        System.out.println("");

        System.out.println("Enter your email");
        String email = Helpers.readLine();

        System.out.println("Enter your password");
        String pass = Helpers.readLine();

        User user = environment.login(email, pass);

        if(user.getEmail().equals("failed_user")){
            System.out.println("Invalid credentials");
            Main.login();
        }

        Main.home(user);
    }

    public static void home(User user) throws IOException, ParseException {
        System.out.println("");
        System.out.println("Select action:");
        System.out.println("1. Create new event");
        System.out.println("2. Create new ticket type");

        int option = Helpers.readInt();

        if(option == 1){
            Event event = eventsContainer.createEvent();
            Boolean save = eventsContainer.storeEvent(event);
            if(save){
                System.out.println("Event " + event.getName() + " successfully saved");
            }
            else{
                System.out.println("Something went wrong, try again");
            }

            Main.home(user);
        }
        if(option == 2) {
            System.out.println("Select event");

            int index = 1;
            ArrayList<String> eventOptions = new ArrayList<>();
            for (Event event : eventsContainer.events) {
                System.out.println(index + ". " + event.getName());
                eventOptions.add(Integer.toString(index));
                index++;
            }

            String select = Helpers.readOption(eventOptions);
            Event event = eventsContainer.events.get(Integer.parseInt(select) - 1);

            TicketType ticketType = event.createNewTicketType();
            Boolean save = event.storeTicketType(ticketType);

            if(save){
                System.out.println("Type " + ticketType.getName() + " successfully saved");
            }
            else{
                System.out.println("Something went wrong, try again");
            }

            Main.home(user);
        }
        else{
            System.out.println("Invalid option");
            Main.home(user);
        }
    }

}

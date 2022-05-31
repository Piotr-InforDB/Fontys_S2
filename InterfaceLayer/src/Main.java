import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class Main {

    public static Environment environment = new Environment(new UsersData());
    public static EventsContainer eventsContainer = new EventsContainer(new EventsData(), new TicketTypesData(), new TicketsData(), new CouponsData());
    public static TicketsManager ticketsManager = new TicketsManager(new TicketsData());

    public static void main(String[] args) throws IOException, ParseException {
        Main.start();
    }

    public static void start() throws IOException, ParseException {
        System.out.println("");
        System.out.println("Select action:");
        System.out.println("1. Tickets");
        System.out.println("2. Login");

        ArrayList<String> options = new ArrayList<String>();
        options.add("1");
        options.add("2");

        String option = Helpers.readOption(options);

        switch (Integer.parseInt(option)){
            case 1 -> Main.tickets();
            case 2 -> Main.login();
        }
    }

    public static void tickets() throws IOException, ParseException {
        System.out.println("");
        System.out.println("Select action:");
        System.out.println("1. Buy ticket");
        System.out.println("2. Show ticket info");
        System.out.println("3. Back");

        ArrayList<String> options = new ArrayList<String>();
        options.add("1");
        options.add("2");
        options.add("3");

        String option = Helpers.readOption(options);

        switch (Integer.parseInt(option)){
            case 1 -> Main.buyTicket();
            case 2 -> Main.findTicket();
            case 3 -> Main.start();
        }
    }
    public static void buyTicket() throws IOException, ParseException {
        System.out.println();
        System.out.println("Select an event");

        int index = 1;
        ArrayList<String> eventOptions = new ArrayList<>();
        for (Event event : eventsContainer.getEvents()) {
            System.out.println(index + ". " + event.getName());
            eventOptions.add(Integer.toString(index));
            index++;
        }

        String select = Helpers.readOption(eventOptions);
        Event event = eventsContainer.getEvents().get(Integer.parseInt(select) - 1);

        Ticket ticket = event.createTicket();
        Boolean save = event.storeTicket(ticket);

        if(!save){
            System.out.println("Something went wrong, please try again");
            Main.buyTicket();
        }

        System.out.println("Ticket has been successfully created, your ticket reference ID is: " + ticket.getToken());
        System.out.println("Would you like to pay now?");
        System.out.println("1. Yes");
        System.out.println("1. No");


        ArrayList<String> payOptions = new ArrayList<>();
        payOptions.add("1");
        payOptions.add("2");

        String payOption = Helpers.readOption(payOptions);

        if(payOption.equals("1")){
            ticket.pay();
        }

        Main.start();
    }
    public static void findTicket() throws IOException, ParseException {
        System.out.println("Input your ticket ID:");
        String token = Helpers.readLine();

        for(Event event : eventsContainer.getEvents()){
            for(Ticket ticket : event.getTickets()){
                System.out.println(ticket.getToken());
                if(ticket.getToken().equals(token)){
                    Main.showTicket(event, ticket);
                }
            }
        }
        System.out.println("Ticket not found!");
        Main.tickets();
    }
    public static void showTicket(Event event, Ticket ticket) throws IOException, ParseException {
        System.out.println(ticket.getCoupon());

        System.out.println("");
        System.out.println("--Ticket--");
        System.out.println("Token: " + ticket.getToken());
        System.out.println("Paid: " + (ticket.getPaid() ? "Yes" : "No"));
        if(ticket.getCoupon() != null){
            System.out.println("Coupon: " + ticket.getCoupon().getCode() + ", "+ticket.getCoupon().getDiscountPercentage()+"% discount");
        }
        System.out.println("Ticket type: " + ticket.getType().getName());
        System.out.println("--Event--");
        System.out.println("Name: " + event.getName());
        System.out.println("Date: " + event.getDate());
        System.out.println("Time: " + event.getStartTime() + " " + event.getEndTime());
        System.out.println("Location: " + event.getLocation());
        System.out.println("--Customer--");
        System.out.println("Name: " + ticket.getCustomer().getName() + " " + ticket.getCustomer().getLastname());
        System.out.println("Email: " + ticket.getCustomer().getEmail());
        System.out.println("Phone: " + ticket.getCustomer().getPhone());
        System.out.println("Date of birth: " + ticket.getCustomer().getDateOfBirth());

        System.out.println("");
        System.out.println("Select action:");
        System.out.println("1. Back");
        if(!ticket.getPaid()){
            System.out.println("2. Pay");
        }

        ArrayList<String> options = new ArrayList<String>();
        options.add("1");
        if(!ticket.getPaid()){
            options.add("2");
        }

        String option = Helpers.readOption(options);

        if(option.equals("1")){
            Main.start();
        }
        else if(option.equals("2")){
            ticket.pay();
        }

        Main.tickets();
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
        System.out.println("2. Manage events");
        System.out.println("3. Back");

        ArrayList<String> options = new ArrayList<String>();
        options.add("1");
        options.add("2");
        options.add("3");

        String option = Helpers.readOption(options);

        if(option.equals("1")){
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
        else if(option.equals("2")) {
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
            Main.manageEvent(event);

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
        else if(option.equals("3")){
            Main.start();
        }
    }

    public static void manageEvent(Event event) throws IOException, ParseException {
        System.out.println("");
        System.out.println("Select action:");
        System.out.println("1. Create new Ticket type");
        System.out.println("2. Create new discount coupon");
        System.out.println("3. Back");

        ArrayList<String> options = new ArrayList<String>();
        options.add("1");
        options.add("2");
        options.add("3");
        String option = Helpers.readOption(options);

        if(option.equals("1")){
            TicketType ticketType = event.createNewTicketType();
            Boolean save = event.storeTicketType(ticketType);

            if(save){
                System.out.println("Type " + ticketType.getName() + " successfully saved");
            }
            else{
                System.out.println("Something went wrong, try again");
            }

            Main.manageEvent(event);
        }
        else if(option.equals("2")){
            DiscountCoupon coupon = event.createNewDiscountCoupon();
            Boolean save = event.storeDiscountCoupon(coupon);

            if(save){
                System.out.println("Coupon " + coupon.getCode() + " successfully saved");
            }
            else{
                System.out.println("Something went wrong, try again");
            }

            Main.manageEvent(event);
        }
        else{
            Main.start();
        }




    }
}

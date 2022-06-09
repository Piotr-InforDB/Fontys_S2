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

        ArrayList<String> options = new ArrayList<>();
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
                if(ticket.getToken().equals(token)){
                    Main.showTicket(event, ticket);
                }
            }
        }
        System.out.println("Ticket not found!");
        Main.tickets();
    }
    public static void showTicket(Event event, Ticket ticket) throws IOException, ParseException {
        if(ticket.isBeeingRefunded()){
            System.out.println("");
            System.out.println("---Ticket is currently beeing refunded!---");
        }

        eventsContainer.showEventInfo(event);
        ticketsManager.showTicketInfo(ticket);

        if(ticket.isBeeingRefunded()){
            Main.tickets();
        }

        System.out.println("");
        System.out.println("Select action:");
        System.out.println("1. Back");
        if(!ticket.getPaid()){
            System.out.println("2. Pay");
        }
        else{
            System.out.println("2. Ask for refund");
        }

        ArrayList<String> options = new ArrayList<>();
        options.add("1");
        options.add("2");

        String option = Helpers.readOption(options);

        if(option.equals("1")){
            Main.start();
        }
        else if(option.equals("2")){
            if(!ticket.getPaid()){
                ticket.pay();
            }
            else{
                ticket.refundInit();
            }
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
        System.out.println("Welcome "+user.getName());
        System.out.println("Select action:");
        System.out.println("1. Back");
        System.out.println("2. Create new event");
        System.out.println("3. Manage events");
        System.out.println("4. Manage refunds");
        if(user.isAdmin()){
            System.out.println("5. Create new user");
        }

        ArrayList<String> options = new ArrayList<>();
        options.add("1");
        options.add("2");
        options.add("3");
        options.add("4");
        if(user.isAdmin()){
            options.add("5");
        }

        String option = Helpers.readOption(options);

        switch (option) {
            case "1" -> Main.start();
            case "2" -> {
                Event event = eventsContainer.createEvent();
                Boolean save = eventsContainer.storeEvent(event);
                if (save) {
                    System.out.println("Event " + event.getName() + " successfully saved");
                }
                else {
                    System.out.println("Something went wrong, try again");
                }

                Main.home(user);
            }
            case "3" -> {
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
            }
            case "4" -> {
                System.out.println("");
                int index = 1;
                for(Event event : eventsContainer.events){
                    for(Ticket ticket : event.getTickets()){
                        if(!ticket.isBeeingRefunded()){continue;}

                        System.out.println("---Ticket " + index + " ---");
                        System.out.println("Event: " + event.getName());
                        System.out.println("Token: " + ticket.getToken());
                        System.out.println("Customer: " + ticket.getCustomer().getName() + " " + ticket.getCustomer().getLastname());
                        System.out.println("Ticket type: " + ticket.getType().getName());

                        ArrayList<String> refundOptions = new ArrayList<>();
                        refundOptions.add("1");
                        refundOptions.add("2");

                        System.out.println("");
                        System.out.println("Confirm the refund:");
                        System.out.println("1. Yes:");
                        System.out.println("2. No:");

                        String refundOption = Helpers.readOption(refundOptions);
                        boolean decision = refundOption.equals("1");

                        ticketsManager.refundClose(event, ticket, decision);


                        index++;
                    }
                }
            }

            case "5" -> {
                User newUser = environment.createNewUser();

                boolean save = environment.storeUser(newUser);
                if (save) {
                    System.out.println("User " + user.getName() + " " + user.getLastname() + " has been successfully created");
                } else {
                    System.out.println("Something went wrong, try again");
                }

                Main.home(user);
            }
        }
    }

    public static void manageEvent(Event event) throws IOException, ParseException {
        System.out.println("");
        System.out.println("Select action:");
        System.out.println("1. Create new Ticket type");
        System.out.println("2. Create new discount coupon");
        System.out.println("3. Show paid tickets");
        System.out.println("4. Back");

        ArrayList<String> options = new ArrayList<>();
        options.add("1");
        options.add("2");
        options.add("3");
        options.add("4");
        String option = Helpers.readOption(options);

        switch (option) {
            case "1" -> {
                System.out.println("Option 1");
                TicketType ticketType = event.createNewTicketType();
                Boolean save = event.storeTicketType(ticketType);

                if (save) {
                    System.out.println("Type " + ticketType.getName() + " successfully saved");
                } else {
                    System.out.println("Something went wrong, try again");
                }

                Main.manageEvent(event);
            }
            case "2" -> {
                DiscountCoupon coupon = event.createNewDiscountCoupon();
                Boolean save = event.storeDiscountCoupon(coupon);

                if (save) {
                    System.out.println("Coupon " + coupon.getCode() + " successfully saved");
                } else {
                    System.out.println("Something went wrong, try again");
                }

                Main.manageEvent(event);
            }
            case "3" -> {
                event.showPaidTickets();
                Main.manageEvent(event);
            }
            default -> Main.start();
        }




    }
}

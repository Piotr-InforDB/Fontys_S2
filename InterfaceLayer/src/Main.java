import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {

    public static Environment environment = new Environment(new UsersData(), new EventsData(), new TicketTypesData(), new TicketsData(), new CouponsData());
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
        for (Event event : environment.getEvents()) {
            System.out.println(index + ". " + event.getName());
            eventOptions.add(Integer.toString(index));
            index++;
        }

        String select = Helpers.readOption(eventOptions);
        Event event = environment.getEvents().get(Integer.parseInt(select) - 1);



        Ticket ticket = Main.createTicket(event);
        Boolean save = event.storeTicket(ticket);

        if(!save){
            System.out.println("Something went wrong, please try again");
            Main.buyTicket();
        }

        System.out.println("Ticket has been successfully created, your ticket reference ID is: " + ticket.getToken());
        System.out.println("Would you like to pay now?");
        System.out.println("1. Yes");
        System.out.println("2. No");


        ArrayList<String> payOptions = new ArrayList<>();
        payOptions.add("1");
        payOptions.add("2");

        String payOption = Helpers.readOption(payOptions);
        if(payOption.equals("1")){
            System.out.println("You can pay € "+ ticket.calcPrice() +" for the ticket using this link: " + ticket.generatePaymentLink());
        }

        Main.start();
    }

    public static void findTicket() throws IOException, ParseException {
        System.out.println("Input your ticket ID:");
        String token = Helpers.readLine();

        for(Event event : environment.getEvents()){
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


        System.out.println("");
        System.out.println("--Event--");
        System.out.println("Name: " + event.getName());
        System.out.println("Date: " + event.getDate());
        System.out.println("Time: " + event.getStartTime() + " " + event.getEndTime());
        System.out.println("Location: " + event.getLocation());

        System.out.println("");
        System.out.println("--Ticket--");
        System.out.println("Token: " + ticket.getToken());
        System.out.println("Paid: " + (ticket.getPaid() ? "Yes" : "No"));
        if(ticket.getCoupon() != null){
            System.out.println("Coupon: " + ticket.getCoupon().getCode() + ", "+ticket.getCoupon().getDiscountPercentage()+"% discount");
        }
        System.out.println("Ticket type: " + ticket.getType().getName());
        System.out.println("");
        System.out.println("--Customer--");
        System.out.println("Name: " + ticket.getCustomer().getName() + " " + ticket.getCustomer().getLastname());
        System.out.println("Email: " + ticket.getCustomer().getEmail());
        System.out.println("Phone: " + ticket.getCustomer().getPhone());
        System.out.println("Date of birth: " + ticket.getCustomer().getDateOfBirth());

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
                System.out.println("You can pay € "+ ticket.calcPrice() +" for the ticket using this link: " + ticket.generatePaymentLink());
            }
            else{
                ticket.refundInit();
                System.out.println("Refund has been initiated.");
            }
        }

        Main.tickets();
    }

    public static Ticket createTicket(Event event) throws IOException {
        System.out.println("Select a ticket type:");

        int index = 1;
        ArrayList<String> eventOptions = new ArrayList<>();
        for (TicketType tp : event.getTicketTypes()) {
            System.out.println(index + ". " + tp.getName() + " €"+tp.getPrice());
            eventOptions.add(Integer.toString(index));
            index++;
        }

        String select = Helpers.readOption(eventOptions);
        TicketType type = event.getTicketTypes().get(Integer.parseInt(select) - 1);

        System.out.println(type.getName());

        System.out.println("First name:");
        String name = Helpers.readLine();

        System.out.println("Last name:");
        String lastname = Helpers.readLine();

        System.out.println("Email:");
        String email = Helpers.readLine();

        System.out.println("Phone:");
        String phone = Helpers.readLine();

        System.out.println("Date of birth:");
        LocalDate dob = Helpers.readDate();

        System.out.println("Do you have a coupoon?:");
        System.out.println("1. Yes");
        System.out.println("2. No");

        ArrayList<String> options = new ArrayList<String>();
        options.add("1");
        options.add("2");

        String cOption = Helpers.readOption(options);

        DiscountCoupon coupon = null;
        if(cOption.equals("1")){
            System.out.println("Enter your coupon:");
            String code = Helpers.readLine();
            for(DiscountCoupon c : event.getCoupons()){
                if (c.getCode().equals(code)){
                    coupon = c;
                    System.out.println("Coupon for "+coupon.getDiscountPercentage()+"% discount has been applied");
                }
            }
        }

        Customer customer = ticketsManager.createCustomer(name, lastname, email, phone, dob);
        return ticketsManager.createTicket(coupon, customer, type);
    }
    public static DiscountCoupon createNewDiscountCoupon() throws IOException {
        System.out.println("Coupon code:");
        String code = Helpers.readLine();

        System.out.println("Coupon discount percentage:");
        double discount = Helpers.readDouble();

        return ticketsManager.createNewDiscountCoupon(code, discount);
    }


    public static void login() throws IOException, ParseException {
        System.out.println("");

        System.out.println("Enter your email");
        String email = Helpers.readLine();

        System.out.println("Enter your password");
        String pass = Helpers.readLine();

        User user = environment.login(email, pass);

        if(user == null){
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
                Event event = Main.createEvent();
                Boolean save = environment.storeEvent(event);
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
                for (Event event : environment.events) {
                    System.out.println(index + ". " + event.getName());
                    eventOptions.add(Integer.toString(index));
                    index++;
                }

                String select = Helpers.readOption(eventOptions);
                Event event = environment.events.get(Integer.parseInt(select) - 1);
                Main.manageEvent(event);
            }
            case "4" -> {
                System.out.println("");
                int index = 1;
                for(Event event : environment.events){
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

                User newUser = Main.createNewUser();
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
                TicketType ticketType = Main.createNewTicketType();
                Boolean save = event.storeTicketType(ticketType);

                if (save) {
                    System.out.println("Type " + ticketType.getName() + " successfully saved");
                } else {
                    System.out.println("Something went wrong, try again");
                }

                Main.manageEvent(event);
            }
            case "2" -> {
                DiscountCoupon coupon = Main.createNewDiscountCoupon();
                Boolean save = event.storeDiscountCoupon(coupon);

                if (save) {
                    System.out.println("Coupon " + coupon.getCode() + " successfully saved");
                } else {
                    System.out.println("Something went wrong, try again");
                }

                Main.manageEvent(event);
            }
            case "3" -> {
                Main.showPaidTickets(event);
                Main.manageEvent(event);
            }
            default -> Main.start();
        }




    }
    public static User createNewUser() throws IOException {
        System.out.println("");

        System.out.println("Name:");
        String name = Helpers.readLine();

        System.out.println("Lastname:");
        String lastname = Helpers.readLine();

        System.out.println("Email:");
        String email = Helpers.readLine();

        System.out.println("Password:");
        String password = Helpers.readLine();

        System.out.println("Is the user an admin:");
        System.out.println("1. Yes");
        System.out.println("1. No");

        ArrayList<String> options = new ArrayList<>();
        options.add("1");
        options.add("2");

        String option = Helpers.readOption(options);

        boolean isAdmin = option.equals("1");

        return environment.createNewUser(name, lastname, email, password, isAdmin);
    }
    public static Event createEvent() throws IOException, ParseException {
        System.out.println("Event name");
        String name = Helpers.readLine();

        System.out.println("Event date YYYY-mm-dd");
        LocalDate date = Helpers.readUpcominDate();

        System.out.println("Start time");
        LocalTime start = Helpers.readTime();

        System.out.println("End time");
        LocalTime end = Helpers.readTime();

        System.out.println("Event location");
        String location = Helpers.readLine();

        return environment.createEvent(name, date, start, end, location);
    }
    public static TicketType createNewTicketType() throws IOException {
        System.out.println("Ticket name:");
        String name = Helpers.readLine();


        System.out.println("Ticket limit:");
        int limit = Helpers.readInt();

        System.out.println("Ticket price:");
        double price = Helpers.readDouble();

        System.out.println("Is price static:");
        System.out.println("1. Yes");
        System.out.println("2. No");

        ArrayList<String> options = new ArrayList<>();
        options.add("1");
        options.add("2");

        String staticOption = Helpers.readOption(options);

        boolean isStatic = staticOption.equals("1");

        return ticketsManager.createNewTicketType(name, limit, price, isStatic);
    }
    public static void showPaidTickets(Event event){
        int index = 0;
        for (Ticket ticket : event.getTickets()) {
            if (!ticket.getPaid()) { continue; }
            index++;

            System.out.println("");
            System.out.println("---Ticket " + index + " ---");
            System.out.println("Token: " + ticket.getToken());
            System.out.println("Customer: " + ticket.getCustomer().getName() + " " + ticket.getCustomer().getLastname());
            System.out.println("Ticket type: " + ticket.getType().getName());
        }

        System.out.println("");
        System.out.println("Tickets paid: "+index+"/"+event.getTickets().size());

    }
}

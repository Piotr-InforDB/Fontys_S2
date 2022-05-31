import Interfaces.IDataModel;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class Event {

    private IDataModel ticketTypesData;
    private IDataModel ticketsData;

    private String name;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;

    private ArrayList<TicketType> ticketTypes;
    private ArrayList<Ticket> tickets;

    public Event(String name, LocalDate date, LocalTime startTime, LocalTime endTime, String location, IDataModel ticketTypesData, IDataModel ticketsData) {
        this.ticketTypesData = ticketTypesData;
        this.ticketsData = ticketsData;

        this.ticketTypes = ticketTypesData.get();
        this.tickets = ticketsData.get();

        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }



    public TicketType createNewTicketType() throws IOException {
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

        boolean isStatic = true;
        if (staticOption.equals("2")) {
            isStatic = false;
        }

        return new TicketType(name, limit, price, isStatic);
    }
    public Boolean storeTicketType(TicketType ticketType){
        try{
           this.ticketTypes.add(ticketType);
           return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public Ticket createTicket() throws IOException {


        System.out.println("Select a ticket type:");

        int index = 1;
        ArrayList<String> eventOptions = new ArrayList<>();
        for (TicketType tp : this.ticketTypes) {
            System.out.println(index + ". " + tp.getName() + " €"+tp.getPrice());
            eventOptions.add(Integer.toString(index));
            index++;
        }

        String select = Helpers.readOption(eventOptions);
        TicketType type = this.ticketTypes.get(Integer.parseInt(select) - 1);

        System.out.println(type.getName());

        System.out.println("First name:");
        String name = Helpers.readLine();

        System.out.println("First name:");
        String lastname = Helpers.readLine();

        System.out.println("Email:");
        String email = Helpers.readLine();

        System.out.println("Phone:");
        String phone = Helpers.readLine();

        System.out.println("Date of birth:");
        LocalDate dob = Helpers.readDate();

        Customer customer = new Customer(name, lastname, email, phone, dob);
        return new Ticket(Helpers.randomString(10), false, customer, type);
    }
    public Boolean storeTicket(Ticket ticket){
        try {
            this.tickets.add(ticket);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }



    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
}



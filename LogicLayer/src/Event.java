import Interfaces.IDataModel;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event {

    private IDataModel ticketTypesData;

    private String name;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;

    private ArrayList<TicketType> ticketTypes;

    public Event(String name, LocalDate date, LocalTime startTime, LocalTime endTime, String location, IDataModel ticketTypesData) {
        this.ticketTypesData = ticketTypesData;

        this.ticketTypes = ticketTypesData.get();

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
}



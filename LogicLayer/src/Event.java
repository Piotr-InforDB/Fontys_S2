import Interfaces.IDataModel;

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



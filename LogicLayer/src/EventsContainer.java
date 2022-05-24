import Interfaces.IDataModel;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EventsContainer {

    private final IDataModel eventsData;
    private final IDataModel ticketTypesData;

    public ArrayList<Event> events;

    public EventsContainer(IDataModel eventsData, IDataModel ticketTypesData) {
        this.eventsData = eventsData;
        this.ticketTypesData = ticketTypesData;

        this.events = eventsData.get();

    }

    public Event createEvent() throws IOException, ParseException {
        System.out.println("Event name");
        String name = Helpers.readLine();

        System.out.println("Event date YYYY-mm-dd");
        String d = Helpers.readLine();
        LocalDate date = LocalDate.parse(d);

        System.out.println("Start time");
        String s = Helpers.readLine();
        LocalTime start = LocalTime.parse(s);

        System.out.println("End time");
        String e = Helpers.readLine();
        LocalTime end = LocalTime.parse(e);

        System.out.println("Event location");
        String location = Helpers.readLine();

        return  new Event(name, date, start, end, location, this.ticketTypesData);
    }
    public Boolean storeEvent(Event event){
        try{
            this.events.add(event);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }


}

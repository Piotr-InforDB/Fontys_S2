import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EventsContainer {

    private IDataModel eventsData;
    private IDataModel ticketTypesData;
    private IDataModel ticketsData;
    private IDataModel couponsData;

    public ArrayList<Event> events;

    public EventsContainer(
            IDataModel eventsData,
            IDataModel ticketTypesData,
            IDataModel ticketsData,
            IDataModel couponsData
    ) {
        this.eventsData = eventsData;
        this.ticketTypesData = ticketTypesData;
        this.ticketsData = ticketsData;
        this.couponsData = couponsData;

        this.events = eventsData.get();
    }

    public Event createEvent() throws IOException, ParseException {
        System.out.println("Event name");
        String name = Helpers.readLine();

        System.out.println("Event date YYYY-mm-dd");
        LocalDate date = Helpers.readDate();

        System.out.println("Start time");
        LocalTime start = Helpers.readTime();

        System.out.println("End time");
        LocalTime end = Helpers.readTime();

        System.out.println("Event location");
        String location = Helpers.readLine();

        return  new Event(name, date, start, end, location, this.ticketTypesData, this.ticketsData, this.couponsData);
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

    public ArrayList<Event> getEvents() {
        return events;
    }
}

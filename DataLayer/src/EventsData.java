import Interfaces.DataModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EventsData implements DataModel {

    public ArrayList<Event> get(){

//        todo change to sql query

        ArrayList<Event> events = new ArrayList<>();

        events.add(new Event("Event 1", LocalDate.parse("2022-01-01"), LocalTime.parse("20:00"), LocalTime.parse("22:00"), "Location"));
        events.add(new Event("Event 2", LocalDate.parse("2022-02-02"), LocalTime.parse("21:00"), LocalTime.parse("23:00"), "Location 2"));


        return events;


    }
}

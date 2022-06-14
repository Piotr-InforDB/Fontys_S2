import java.util.ArrayList;

public class TicketTypesData implements IDataModel {

    public ArrayList<TicketType> get(int eventId){

//        ToDo: change to sql query, fetch all ticketTypes with corresponding event id

        ArrayList<TicketType> ticketTypes = new ArrayList<>();

        ticketTypes.add(new TicketType("Normal", 100, 59.99, false));
        ticketTypes.add(new TicketType("V.I.P.", 25, 129.99, false));

        return ticketTypes;


    }
}

import Interfaces.IDataModel;

import java.util.ArrayList;

public class TicketTypesData implements IDataModel {

    public ArrayList<TicketType> get(){

//        todo change to sql query

        ArrayList<TicketType> ticketTypes = new ArrayList<>();

        ticketTypes.add(new TicketType("AhJhdVnyF", false));
        ticketTypes.add(new TicketType("aDgeASdVG", true));


        return ticketTypes;


    }
}

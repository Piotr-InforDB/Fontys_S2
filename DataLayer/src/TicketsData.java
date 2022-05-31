import Interfaces.IDataModel;

import java.time.LocalDate;
import java.util.ArrayList;

public class TicketsData implements IDataModel {

    public ArrayList<Ticket> get(){

//        todo change to sql query

        ArrayList<Ticket> tickets = new ArrayList<>();

        tickets.add(
                new Ticket(
                        Helpers.randomString(10),
                  true,
                        new Customer("Piotr", "Tadrala", "infamous18862@gmail.com", "0641073745", LocalDate.parse("2001-12-22")),
                        new TicketType("Normal", 100, 59.99, false)
                )
        );
        tickets.add(
                new Ticket(
                        Helpers.randomString(10),
                        true,
                        new Customer("Piotr", "Tadrala", "infamous18862@gmail.com", "0641073745", LocalDate.parse("2001-12-22")),
                        new TicketType("V.I.P.", 25, 129.99, false)
                )
        );

        return tickets;


    }

}

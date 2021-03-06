import java.time.LocalDate;
import java.util.ArrayList;

public class TicketsData implements IDataModel {

    public ArrayList<Ticket> get(int eventId){

//        ToDo: change to sql query, fetch all tickets with corresponding event id

        ArrayList<Ticket> tickets = new ArrayList<>();

        tickets.add(
                new Ticket(
                    "ASDASD",
                    false,
                    false,
                    new DiscountCoupon("COUPON", 10),
                    new Customer("Piotr", "Tadrala", "infamous18862@gmail.com", "0641073745", LocalDate.parse("2001-05-30")),
                    new TicketType("Normal", 100, 59.99, false)
                )
        );
        tickets.add(
                new Ticket(
                    "ASDASD2",
                    true,
                    false,
                    new DiscountCoupon("COUPON", 10),
                    new Customer("Piotr", "Tadrala", "infamous18862@gmail.com", "0641073745", LocalDate.parse("2001-05-30")),
                    new TicketType("Normal", 100, 59.99, false)
                )
        );
        tickets.add(
                new Ticket(
                    "DSADSA",
                    false,
                    false,
                    null,
                    new Customer("Piotr", "Tadrala", "infamous18862@gmail.com", "0641073745", LocalDate.parse("2001-12-22")),
                    new TicketType("V.I.P.", 25, 129.99, true)
                )
        );
        tickets.add(
                new Ticket(
                    "DSADSA2",
                    true,
                    false,
                    null,
                    new Customer("Piotr", "Tadrala", "infamous18862@gmail.com", "0641073745", LocalDate.parse("2001-12-22")),
                    new TicketType("V.I.P.", 25, 129.99, true)
                )
        );

        return tickets;


    }

}

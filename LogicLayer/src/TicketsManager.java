import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TicketsManager {

    private IDataModel ticketsData;
    public TicketsManager(IDataModel ticketsData) {
        this.ticketsData = ticketsData;
    }

    public void refundClose(Event event, Ticket ticket, Boolean decision){
        ticket.refundClose();

        if(decision){
            event.removeTicket(ticket.getToken());
            ticket.refundClose();
        }
    }

    public Customer createCustomer(String name, String lastname, String email, String phone, LocalDate dob){
        return new Customer(name, lastname, email, phone, dob);
    }
    public Ticket createTicket(DiscountCoupon coupon, Customer customer, TicketType type) throws IOException {
        return new Ticket(Helpers.randomString(10), false, false, coupon, customer, type);
    }
    public TicketType createNewTicketType(String name, int limit, double price, boolean isStatic) throws IOException {
        return new TicketType(name, limit, price, isStatic);
    }
    public DiscountCoupon createNewDiscountCoupon(String code, double discount) throws IOException {
        return new DiscountCoupon(code, discount);
    }


}

import java.util.ArrayList;

public class TicketsManager {

    private IDataModel ticketsData;
    public TicketsManager(IDataModel ticketsData) {
        this.ticketsData = ticketsData;
    }

    public void showTicketInfo(Ticket ticket){
        System.out.println("");
        System.out.println("--Ticket--");
        System.out.println("Token: " + ticket.getToken());
        System.out.println("Paid: " + (ticket.getPaid() ? "Yes" : "No"));
        if(ticket.getCoupon() != null){
            System.out.println("Coupon: " + ticket.getCoupon().getCode() + ", "+ticket.getCoupon().getDiscountPercentage()+"% discount");
        }
        System.out.println("Ticket type: " + ticket.getType().getName());
        System.out.println("");
        System.out.println("--Customer--");
        System.out.println("Name: " + ticket.getCustomer().getName() + " " + ticket.getCustomer().getLastname());
        System.out.println("Email: " + ticket.getCustomer().getEmail());
        System.out.println("Phone: " + ticket.getCustomer().getPhone());
        System.out.println("Date of birth: " + ticket.getCustomer().getDateOfBirth());
    }

    public void refundClose(Event event, Ticket ticket, Boolean decision){
        ticket.refundClose();

        if(decision){
            event.removeTicket(ticket.getToken());
            ticket.refundClose();
        }
    }

}

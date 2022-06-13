import java.io.IOException;
import java.time.LocalDate;
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

    public Ticket createTicket(Event event) throws IOException {


        System.out.println("Select a ticket type:");

        int index = 1;
        ArrayList<String> eventOptions = new ArrayList<>();
        for (TicketType tp : event.getTicketTypes()) {
            System.out.println(index + ". " + tp.getName() + " €"+tp.getPrice());
            eventOptions.add(Integer.toString(index));
            index++;
        }

        String select = Helpers.readOption(eventOptions);
        TicketType type = event.getTicketTypes().get(Integer.parseInt(select) - 1);

        System.out.println(type.getName());

        System.out.println("First name:");
        String name = Helpers.readLine();

        System.out.println("First name:");
        String lastname = Helpers.readLine();

        System.out.println("Email:");
        String email = Helpers.readLine();

        System.out.println("Phone:");
        String phone = Helpers.readLine();

        System.out.println("Date of birth:");
        LocalDate dob = Helpers.readDate();

        System.out.println("Do you have a coupoon?:");
        System.out.println("1. Yes");
        System.out.println("2. No");

        ArrayList<String> options = new ArrayList<String>();
        options.add("1");
        options.add("2");

        String cOption = Helpers.readOption(options);

        DiscountCoupon coupon = null;
        if(cOption.equals("1")){
            System.out.println("Enter your coupon:");
            String code = Helpers.readLine();
            for(DiscountCoupon c : event.getCoupons()){
                if (c.getCode().equals(code)){
                    coupon = c;
                    System.out.println("Coupon for "+coupon.getDiscountPercentage()+"% discount has been applied");
                }
            }
        }

        Customer customer = new Customer(name, lastname, email, phone, dob);
        return new Ticket(Helpers.randomString(10), false, false, coupon, customer, type);
    }

    public TicketType createNewTicketType() throws IOException {
        System.out.println("Ticket name:");
        String name = Helpers.readLine();


        System.out.println("Ticket limit:");
        int limit = Helpers.readInt();

        System.out.println("Ticket price:");
        double price = Helpers.readDouble();


        System.out.println("Is price static:");
        System.out.println("1. Yes");
        System.out.println("2. No");
        ArrayList<String> options = new ArrayList<>();
        options.add("1");
        options.add("2");
        String staticOption = Helpers.readOption(options);

        boolean isStatic = true;
        if (staticOption.equals("2")) {
            isStatic = false;
        }

        return new TicketType(name, limit, price, isStatic);
    }

    public DiscountCoupon createNewDiscountCoupon() throws IOException {
        System.out.println("Coupon code:");
        String code = Helpers.readLine();

        System.out.println("Coupon discount percentage:");
        double discount = Helpers.readDouble();

        return new DiscountCoupon(code, discount);
    }

}

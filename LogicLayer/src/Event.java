import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event {

    private IDataModel ticketTypesData;
    private IDataModel ticketsData;
    private IDataModel couponsData;

    private String name;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;

    private ArrayList<TicketType> ticketTypes;
    private ArrayList<Ticket> tickets;
    private ArrayList<DiscountCoupon> coupons;

    public Event(
            String name,
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime,
            String location,
            IDataModel ticketTypesData,
            IDataModel ticketsData,
            IDataModel couponsData
    ) {
        this.ticketTypesData = ticketTypesData;
        this.ticketsData = ticketsData;
        this.couponsData = couponsData;

        this.ticketTypes = this.ticketTypesData.get();
        this.tickets = this.ticketsData.get();
        this.coupons = this.couponsData.get();

        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
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
    public Boolean storeTicketType(TicketType ticketType){
        try{
           this.ticketTypes.add(ticketType);
           return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public DiscountCoupon createNewDiscountCoupon() throws IOException {
        System.out.println("Coupon code:");
        String code = Helpers.readLine();

        System.out.println("Coupon discount percentage:");
        double discount = Helpers.readDouble();

        return new DiscountCoupon(code, discount);
    }
    public Boolean storeDiscountCoupon(DiscountCoupon coupon){
        try{
           this.coupons.add(coupon);
           return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public Ticket createTicket() throws IOException {


        System.out.println("Select a ticket type:");

        int index = 1;
        ArrayList<String> eventOptions = new ArrayList<>();
        for (TicketType tp : this.ticketTypes) {
            System.out.println(index + ". " + tp.getName() + " €"+tp.getPrice());
            eventOptions.add(Integer.toString(index));
            index++;
        }

        String select = Helpers.readOption(eventOptions);
        TicketType type = this.ticketTypes.get(Integer.parseInt(select) - 1);

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
            for(DiscountCoupon c : this.coupons){
                if (c.getCode().equals(code)){
                    coupon = c;
                    System.out.println("Coupon for "+coupon.getDiscountPercentage()+"% discount has been applied");
                }
            }
        }

        Customer customer = new Customer(name, lastname, email, phone, dob);
        return new Ticket(Helpers.randomString(10), false, coupon, customer, type);
    }
    public Boolean storeTicket(Ticket ticket){
        try {
            this.tickets.add(ticket);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }



    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
}



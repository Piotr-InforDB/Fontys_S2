public class Ticket {

    private String token;
    private Boolean isPaid;
    private Customer customer;
    private TicketType type;

    public Ticket(String token, Boolean isPaid, Customer customer, TicketType type) {
        this.token = token;
        this.isPaid = isPaid;
        this.customer = customer;
        this.type = type;
    }

    public void pay(){

    }

    public String getToken() {
        return token;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public TicketType getType() {
        return type;
    }
}

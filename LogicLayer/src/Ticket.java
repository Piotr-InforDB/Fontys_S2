public class Ticket {

    private String token;
    private Boolean isPaid;
    private DiscountCoupon coupon = null;
    private Customer customer;
    private TicketType type;

    public Ticket(String token, Boolean isPaid, DiscountCoupon coupon, Customer customer, TicketType type) {
        this.token = token;
        this.isPaid = isPaid;
        this.coupon = coupon;
        this.customer = customer;
        this.type = type;
    }

    public void pay(){
        DiscountCalculator calculator = new DiscountCalculator(this);

        double price = calculator.calcNewPrice();
        System.out.println("You can pay € "+price+" for the ticket using this link: " + this.generatePaymentLink());
    }
    public String generatePaymentLink(){
//        ToDO  generate real payment link using external payment gateway
        return "www.paymentlink.com/F2TICKETS/" + this.token;
    }

    public String getToken() {
        return token;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public DiscountCoupon getCoupon() {
        return coupon;
    }

    public Customer getCustomer() {
        return customer;
    }

    public TicketType getType() {
        return type;
    }
}

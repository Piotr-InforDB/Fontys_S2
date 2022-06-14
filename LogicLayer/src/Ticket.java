public class Ticket {

    private String token;
    private boolean isPaid;
    private boolean isBeeingRefunded;
    private DiscountCoupon coupon = null;
    private Customer customer;
    private TicketType type;

    public Ticket(String token, boolean isPaid, boolean isBeeingRefunded, DiscountCoupon coupon, Customer customer, TicketType type) {
        this.token = token;
        this.isPaid = isPaid;
        this.isBeeingRefunded = isBeeingRefunded;
        this.coupon = coupon;
        this.customer = customer;
        this.type = type;
    }

    public double calcPrice(){
        DiscountCalculator calculator = new DiscountCalculator(this);
        return calculator.calcNewPrice();
    }
    public void refundInit(){
        this.isBeeingRefunded = true;
    }
    public void refundClose(){
        this.isBeeingRefunded = false;
    }

    public String generatePaymentLink(){
//        ToDO  generate real payment link using external payment gateway
        return "www.paymentlink.com/F2TICKETS/" + this.token;
    }

    public String getToken() {
        return token;
    }

    public boolean isBeeingRefunded() {
        return isBeeingRefunded;
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

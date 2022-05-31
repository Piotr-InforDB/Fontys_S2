
public class Coupon implements IDiscountRule{

    public Boolean isValid(Ticket ticket) {
        return ticket.getCoupon() != null;
    }

    public Double calcNewPrice(Ticket ticket) {
        return ticket.getType().getPrice() * ((100 - ticket.getCoupon().getDiscountPercentage()) / 100);
    }
}

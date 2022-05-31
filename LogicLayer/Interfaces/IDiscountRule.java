public interface IDiscountRule {

    double discountPerc = 0.00;
    
    Boolean isValid(Ticket ticket);
    Double calcNewPrice(Ticket ticket);

}

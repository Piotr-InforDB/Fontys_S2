package Interfaces;

public interface IDiscountRule {

    double discountPerc = 0.00;
    
    Boolean isValid();
    Double calcNewPrice();

}

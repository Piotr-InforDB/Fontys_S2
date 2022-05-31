package DiscountRules;

import Interfaces.IDiscountRule;
public class Birthday implements IDiscountRule {

    double discountPerc = 10;

    public Boolean isValid(Ticket ticket) {
        return null;
    }

    public Double calcNewPrice() {
        return 0.00;
    }
}

import DiscountRules.Birthday;
import Interfaces.IDiscountRule;
import java.util.ArrayList;

public class DiscountCalculator{

    private Ticket ticket;
    private ArrayList<IDiscountRule> rules;

    public DiscountCalculator(Ticket ticket) {
        this.ticket = ticket;

        this.rules.add(new Birthday());

    }

    public double calcNewPrice(){
        double price = this.ticket.getType().getPrice();


        for(IDiscountRule rule : this.rules){
            if(rule.isValid(this.ticket)){
                price = rule.calcNewPrice(ticket);
            }
        }

        return 0.00;
    }


}

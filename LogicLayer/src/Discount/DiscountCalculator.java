import java.text.DecimalFormat;
import java.util.ArrayList;

public class DiscountCalculator{

    private Ticket ticket;
    private ArrayList<IDiscountRule> rules = new ArrayList<>();

    public DiscountCalculator(Ticket ticket) {
        this.ticket = ticket;

        this.rules.add(new Birthday());
        this.rules.add(new Coupon());
    }

    public double calcNewPrice(){
        double price = 0;

        if(this.ticket.getType().isStatic()){
            return this.ticket.getType().getPrice();
        }

        int validRules = 0;
        for(IDiscountRule rule : this.rules){
            if(rule.isValid(this.ticket)){
                validRules++;
                price += rule.calcNewPrice(this.ticket);
            }
        }

        if(validRules > 0){
            price = price / validRules;
        }
        else{
            price = ticket.getType().getPrice();
        }

        return Math.round(price * 100.00) / 100.00;
    }


}

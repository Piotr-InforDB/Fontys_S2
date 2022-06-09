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
        DecimalFormat df = new DecimalFormat("0.00");
        double price = this.ticket.getType().getPrice();

        if(this.ticket.getType().isStatic()){
            return this.ticket.getType().getPrice();
        }

        for(IDiscountRule rule : this.rules){
            if(rule.isValid(this.ticket)){
                price = rule.calcNewPrice(this.ticket);
}
        }


        return Double.parseDouble(df.format(price));
    }


}

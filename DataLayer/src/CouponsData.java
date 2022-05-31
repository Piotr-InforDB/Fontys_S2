import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CouponsData implements IDataModel {

    public ArrayList<DiscountCoupon> get(){

        //todo change to sql query
        ArrayList<DiscountCoupon> coupons = new ArrayList<>();

        coupons.add(new DiscountCoupon("FREE", 99.99));
        coupons.add(new DiscountCoupon("FIFTY", 50));

        return coupons;

    }
}

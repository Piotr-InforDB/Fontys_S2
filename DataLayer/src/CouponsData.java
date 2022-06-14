import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CouponsData implements IDataModel {

    public ArrayList<DiscountCoupon> get(int eventId){

//        ToDo: change to sql query, fetch all coupons with corresponding event id

        ArrayList<DiscountCoupon> coupons = new ArrayList<>();

        coupons.add(new DiscountCoupon("FREE", 99.99));
        coupons.add(new DiscountCoupon("FIFTY", 50));

        return coupons;

    }
}

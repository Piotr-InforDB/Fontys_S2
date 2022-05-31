public class DiscountCoupon {

    private String code;
    private double discountPercentage;

    public DiscountCoupon(String code, double discountPercentage) {
        this.code = code;
        this.discountPercentage = discountPercentage;
    }

    public String getCode() {
        return code;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }
}

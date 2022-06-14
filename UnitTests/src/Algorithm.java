import org.testng.annotations.Test;
import java.time.LocalDate;
import static org.testng.AssertJUnit.assertEquals;

public class Algorithm {

    @Test
    public void noDiscount(){
        Ticket ticket = new Ticket(
                            "ASDASD",
                            false,
                            false,
                            null,
                            new Customer("Piotr", "Tadrala", "infamous18862@gmail.com", "0641073745", LocalDate.parse("2001-05-30")),
                            new TicketType("Normal", 100, 100, false)
                        );

        DiscountCalculator calculator = new DiscountCalculator(ticket);

        assertEquals(100.00, calculator.calcNewPrice());
    }

    @Test
    public void discountButStatic(){
        Ticket ticket = new Ticket(
                "ASDASD",
                false,
                false,
                new DiscountCoupon("COUPON", 10),
                new Customer("Piotr", "Tadrala", "infamous18862@gmail.com", "0641073745", LocalDate.parse("2001-05-30")),
                new TicketType("Normal", 100, 100, true)
        );

        DiscountCalculator calculator = new DiscountCalculator(ticket);

        assertEquals(100.00, calculator.calcNewPrice());
    }

    @Test
    public void couponDiscount(){
        Ticket ticket = new Ticket(
                "ASDASD",
                false,
                false,
                new DiscountCoupon("COUPON", 50),
                new Customer("Piotr", "Tadrala", "infamous18862@gmail.com", "0641073745", LocalDate.parse("2001-05-30")),
                new TicketType("Normal", 100, 100, false)
        );

        DiscountCalculator calculator = new DiscountCalculator(ticket);

        assertEquals(50.00, calculator.calcNewPrice());
    }

    @Test
    public void birthdayDiscount(){
        Ticket ticket = new Ticket(
                "ASDASD",
                false,
                false,
                null,
                new Customer("Piotr", "Tadrala", "infamous18862@gmail.com", "0641073745", LocalDate.now()),
                new TicketType("Normal", 100, 100, false)
        );

        DiscountCalculator calculator = new DiscountCalculator(ticket);

        assertEquals(90.00, calculator.calcNewPrice());
    }

    @Test
    public void birthdayAndCouponDiscount(){
        Ticket ticket = new Ticket(
                "ASDASD",
                false,
                false,
                new DiscountCoupon("COUPON", 20),
                new Customer("Piotr", "Tadrala", "infamous18862@gmail.com", "0641073745", LocalDate.now()),
                new TicketType("Normal", 100, 100, false)
        );

        DiscountCalculator calculator = new DiscountCalculator(ticket);

        assertEquals(85.00, calculator.calcNewPrice());

    }
}

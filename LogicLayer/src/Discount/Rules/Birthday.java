import java.time.LocalDate;

public class Birthday implements IDiscountRule {

    double discountPerc = 10;

    public Double calcNewPrice(Ticket ticket) {
        return ticket.getType().getPrice() * ((100 - this.discountPerc) / 100);
    }

    public Boolean isValid(Ticket ticket) {
        LocalDate dob = ticket.getCustomer().getDateOfBirth();
        LocalDate now = LocalDate.now();

        return now.getMonth().equals(dob.getMonth()) && now.getDayOfMonth() == dob.getDayOfMonth();
    }
}

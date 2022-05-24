import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;

public class TicketType {

    private String name;
    private int limit;
    private double price;
    private boolean isStatic;

    public TicketType(String name, int limit, double price, boolean isStatic) {
        this.name = name;
        this.limit = limit;
        this.price = price;
        this.isStatic = isStatic;
    }

    public String getName() {
        return name;
    }

    public int getLimit() {
        return limit;
    }

    public double getPrice() {
        return price;
    }

    public boolean isStatic() {
        return isStatic;
    }
}

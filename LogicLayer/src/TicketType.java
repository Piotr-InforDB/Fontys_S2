import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;

public class TicketType {

    private String token;
    private boolean isPaid;

    public TicketType(String token, Boolean isPaid) {
        this.token = token;
        this.isPaid = isPaid;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public String getToken() {
        return token;
    }
}

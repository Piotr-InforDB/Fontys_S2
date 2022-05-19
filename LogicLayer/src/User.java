import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class User {

    private String name;
    private String lastname;
    private String email;
    private String password;
    private boolean isAdmin;

    public User(String name, String lastname, String email, String password, boolean isAdmin) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Event createEvent() throws IOException, ParseException {
        System.out.println("Event name");
        String name = Helpers.readLine();

        System.out.println("Event date YYYY-mm-dd");
        String d = Helpers.readLine();
        LocalDate date = LocalDate.parse(d);

        System.out.println("Start time");
        String s = Helpers.readLine();
        LocalTime start = LocalTime.parse(s);

        System.out.println("End time");
        String e = Helpers.readLine();
        LocalTime end = LocalTime.parse(e);

        System.out.println("Event location");
        String location = Helpers.readLine();

        Event event =  new Event(name, date, start, end, location);

        return event;

    }




    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;

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

import java.time.LocalDate;

public class Customer {

    private String name;
    private String lastname;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;


    public Customer(String name, String lastname, String email, String phone, LocalDate dateOfBirth) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
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

    public String getPhone() {
        return phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}

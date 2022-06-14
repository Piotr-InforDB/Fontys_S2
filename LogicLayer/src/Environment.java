import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Environment {

    private int id;
    private IDataModel usersData;
    private IDataModel eventsData;
    private IDataModel ticketsData;
    private IDataModel ticketTypesData;
    private IDataModel couponsData;

    private ArrayList<User> users;
    public ArrayList<Event> events;

    public Environment(
            IDataModel usersData,
            IDataModel eventsData,
            IDataModel ticketTypesData,
            IDataModel ticketsData,
            IDataModel couponsData
    ) {
        this.usersData = usersData;
        this.eventsData = eventsData;
        this.ticketTypesData = ticketTypesData;
        this.ticketsData = ticketsData;
        this.couponsData = couponsData;

        this.events = eventsData.get(this.id);
        this.users = usersData.get(this.id);
    }

    public User login(String email, String pass){

        for(User user : this.users){
            if(email.equals(user.getEmail()) && Helpers.hash(pass).equals(user.getPassword())){
                return user;
            }
        }

        return null;
    }


    public User createNewUser(String name, String lastname, String email, String password, boolean isAdmin) throws IOException {
        return new User(name, lastname, email, Helpers.hash(password), isAdmin);
    }
    public boolean storeUser(User user){
        try{
            this.users.add(user);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }


    public Event createEvent(String name, LocalDate date, LocalTime start, LocalTime end, String location) throws IOException, ParseException {
        return  new Event(name, date, start, end, location, this.ticketTypesData, this.ticketsData, this.couponsData);
    }
    public Boolean storeEvent(Event event){
        try{
            this.events.add(event);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }


    public ArrayList<Event> getEvents() {
        return events;
    }
    public ArrayList<User> getUsers() {
        return users;
    }
}

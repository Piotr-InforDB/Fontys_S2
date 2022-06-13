import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Environment {

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

        this.events = eventsData.get();
        this.users = usersData.get();
    }

    public Event createEvent() throws IOException, ParseException {
        System.out.println("Event name");
        String name = Helpers.readLine();

        System.out.println("Event date YYYY-mm-dd");
        LocalDate date = Helpers.readDate();

        System.out.println("Start time");
        LocalTime start = Helpers.readTime();

        System.out.println("End time");
        LocalTime end = Helpers.readTime();

        System.out.println("Event location");
        String location = Helpers.readLine();

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
    public void showEventInfo(Event event){
        System.out.println("");
        System.out.println("--Event--");
        System.out.println("Name: " + event.getName());
        System.out.println("Date: " + event.getDate());
        System.out.println("Time: " + event.getStartTime() + " " + event.getEndTime());
        System.out.println("Location: " + event.getLocation());
    }

    public User login(String email, String pass){

        for(User user : this.users){
            if(email.equals(user.getEmail()) && Helpers.hash(pass).equals(user.getPassword())){
                return user;
            }
        }

        return new User("", "", "failed_user", "", false);

    }

    public User createNewUser() throws IOException {
        System.out.println("");

        System.out.println("Name:");
        String name = Helpers.readLine();

        System.out.println("Lastname:");
        String lastname = Helpers.readLine();

        System.out.println("Email:");
        String email = Helpers.readLine();

        System.out.println("Password:");
        String password = Helpers.readLine();

        System.out.println("Is the user an admin:");
        System.out.println("1. Yes");
        System.out.println("1. No");

        ArrayList<String> options = new ArrayList<>();
        options.add("1");
        options.add("2");

        String option = Helpers.readOption(options);

        boolean isAdmin = option.equals("1");


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


    public ArrayList<User> getUsers() {
        return users;
    }
}

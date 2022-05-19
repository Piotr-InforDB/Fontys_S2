import Interfaces.DataModel;

import java.lang.reflect.Array;
import java.security.spec.ECField;
import java.util.ArrayList;

public class Environment {

    private DataModel eventsData;
    private DataModel usersData;

    private ArrayList<Event> events;
    private ArrayList<User> users;

    public Environment(DataModel eventsData, DataModel usersData) {

        this.eventsData = eventsData;
        this.usersData = usersData;


        this.events = eventsData.get();
        this.users = usersData.get();
    }

    public User login(String email, String pass){

        for(User user : this.users){
            if(email.equals(user.getEmail()) && Helpers.hash(pass).equals(user.getPassword())){
                return user;
            }
        }

        return new User("", "", "failed_user", "", false);

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


}

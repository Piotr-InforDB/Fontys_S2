import Interfaces.IDataModel;

import java.util.ArrayList;

public class Environment {

    private IDataModel eventsData;
    private IDataModel usersData;
    private IDataModel ticketTypesData;

    private ArrayList<User> users;

    public Environment(IDataModel usersData) {
        this.usersData = usersData;


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




}

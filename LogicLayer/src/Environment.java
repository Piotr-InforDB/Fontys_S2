import Interfaces.DataModel;

import java.util.ArrayList;

public class Environment {

    public static User login(DataModel model, String email, String pass){

        ArrayList<User> users = model.get();

        for(User user : users){
            if(email.equals(user.getEmail()) && Helpers.hash(pass).equals(user.getPassword())){
                return user;
            }
        }

        return new User("", "", "failed_user", "", false);

    }

}

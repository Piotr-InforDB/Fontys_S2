import Interfaces.IDataModel;

import java.util.ArrayList;

public class UsersData implements IDataModel {

    public ArrayList<User> get(){

//        todo change to sql query

        ArrayList<User> users = new ArrayList<>();

        users.add(new User("Piotr", "Tadrala", "email", Helpers.hash("admin"), true));
        users.add(new User("Piotr 2", "Tadrala 2", "email", Helpers.hash("admin"), true));

        return users;


    }
}

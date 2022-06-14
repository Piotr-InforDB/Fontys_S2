import java.util.ArrayList;

public class UsersData implements IDataModel {

    public ArrayList<User> get(int environmentId){

//        ToDo: change to sql query, fetch all users with corresponding event id

        ArrayList<User> users = new ArrayList<>();

        users.add(new User("Piotr", "Tadrala", "admin", Helpers.hash("admin"), true));
        users.add(new User("Piotr 2", "Tadrala 2", "email", Helpers.hash("admin"), true));

        return users;


    }
}

import java.io.IOException;
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




}

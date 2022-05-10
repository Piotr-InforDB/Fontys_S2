import Helpers.Helpers;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("");
        System.out.println("Select action:");
        System.out.println("1. Buy ticket");
        System.out.println("2. Login");
        System.out.print(">");

        String sOption = Helpers.readLine();

        if(!Helpers.isInt(sOption)){
            System.out.println("Invalid option");
            Main.main(args);
        }

        int option = Integer.parseInt(sOption);

        if(option == 1){
            System.out.println("Selected option: Buy ticket");
        }
        else if(option == 2){
            System.out.println("Selected option: Login");
            Main.login();
        }
        else{
            System.out.println("Invalid option");
            Main.main(args);
        }

    }

    public static void login() throws IOException{
        System.out.println("");
        System.out.println("Enter your email");
        System.out.print(">");

        String email = Helpers.readLine();

        System.out.println("Enter your email");
        System.out.print(">");

        String pass = Helpers.readLine();

        boolean isLoggedIn = Environment.login(email, pass);
    }

}

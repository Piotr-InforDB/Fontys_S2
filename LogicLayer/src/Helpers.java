import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Helpers {

    public static String readLine() throws IOException {
        while(true){
            System.out.print(">");
            String line = (new BufferedReader(new InputStreamReader(System.in))).readLine();
            if(line.equals("")){
                System.out.println("Input can't be empty");
                continue;
            }
            return line;
        }
    }
    public static int readInt() throws IOException {
        while(true){
            String sOption = Helpers.readLine();
            if(!isInt(sOption)){
                System.out.println("Invalid option");
                System.out.print(">");
                continue;
            }
            return Integer.parseInt(sOption);
        }
    }
    public static LocalDate readDate() throws IOException {
        while(true){
            String option = Helpers.readLine();
            if(!isDate(option)){
                System.out.println("Invalid date");
                continue;
            }
            return LocalDate.parse(option);
        }
    }
    public static LocalDate readUpcominDate() throws IOException {
        while(true){
            String option = Helpers.readLine();
            if(!isDate(option)){
                System.out.println("Invalid date");
                continue;
            }
            LocalDate date = LocalDate.parse(option);

            if(date.isBefore(LocalDate.now())){
                System.out.println("can't use date from the past");
                continue;
            }

            return date;
        }
    }
    public static LocalTime readTime() throws IOException {
        while(true){
            String option = Helpers.readLine();
            if(!isTime(option)){
                System.out.println("Invalid time");
                continue;
            }
            return LocalTime.parse(option);
        }
    }
    public static double readDouble() throws IOException {
        while(true){
            String sOption = Helpers.readLine();
            if(!isNumeric(sOption)){
                System.out.println("Invalid option");
                continue;
            }
            return Double.parseDouble(sOption);
        }
    }
    public static String readOption(ArrayList<String> array) throws IOException {
        while(true){
            String option = Helpers.readLine();
            for(String opt : array){
                if(opt.equals(option)){
                    return option;
                }
            }
            System.out.println("Invalid option");
        }
    }



    public static String hash(String string){
        String hash = "";

        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());
            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }
        catch(Exception e){
            return hash;
        }
    }



    public static boolean isInt(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isDate(String str) {
        try {
            LocalDate date = LocalDate.parse(str);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    public static boolean isTime(String str) {
        try {
            LocalTime time = LocalTime.parse(str);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public static String randomString(int length){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < length; i++) {

            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
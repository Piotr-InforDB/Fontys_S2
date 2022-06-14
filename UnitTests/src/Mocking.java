import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

public class Mocking {

    @Test
    public void realData(){

        Environment environment = new Environment(new UsersData(), new EventsData(), new TicketTypesData(), new TicketsData(), new CouponsData());
        User user = environment.login("admin", "admin");
        assertEquals("Piotr", user.getName());
    }

    @Test
    public void realDataInvalidUser(){
        Environment environment = new Environment(new UsersData(), new EventsData(), new TicketTypesData(), new TicketsData(), new CouponsData());
        User user = environment.login("ASD", "DSA");
        assertNull(user);
    }

    @Test
    public void MockedUsersData(){
        Environment environment = new Environment(new UsersMockData(), new EventsData(), new TicketTypesData(), new TicketsData(), new CouponsData());
        User user = environment.login("admin", "admin");
        assertNull(user);
    }



}

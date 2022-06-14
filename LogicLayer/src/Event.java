import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event {

    private IDataModel ticketTypesData;
    private IDataModel ticketsData;
    private IDataModel couponsData;

    private int id;
    private String name;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;

    private ArrayList<TicketType> ticketTypes;
    private ArrayList<Ticket> tickets;
    private ArrayList<DiscountCoupon> coupons;

    public Event(
            String name,
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime,
            String location,

            IDataModel ticketTypesData,
            IDataModel ticketsData,
            IDataModel couponsData
    ) {
        this.ticketTypesData = ticketTypesData;
        this.ticketsData = ticketsData;
        this.couponsData = couponsData;

        this.ticketTypes = this.ticketTypesData.get(this.id);
        this.tickets = this.ticketsData.get(this.id);
        this.coupons = this.couponsData.get(this.id);

        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }



    public Boolean storeTicketType(TicketType ticketType){
        try{
           this.ticketTypes.add(ticketType);
           return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public Boolean storeDiscountCoupon(DiscountCoupon coupon){
        try{
           this.coupons.add(coupon);
           return true;
        }
        catch (Exception e){
            return false;
        }
    }


    public Boolean storeTicket(Ticket ticket){
        try {
            this.tickets.add(ticket);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public void removeTicket(String token){
        int index = 0;
        for(Ticket ticket : this.tickets){
            if(ticket.getToken().equals(token)){
                this.tickets.remove(index);
            }
            index++;
        }
    }


    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public ArrayList<TicketType> getTicketTypes() {
        return ticketTypes;
    }

    public ArrayList<DiscountCoupon> getCoupons() {
        return coupons;
    }
}



import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter
public class Ticket {

    String ticketNo;
    long starTime;
    long endTime;
    Vehicle vehicle;
    ParkingSlot parkingSlot;


    public static Ticket createTicket(Vehicle vehicle, ParkingSlot parkingSlot) {
        return Ticket.builder().parkingSlot(parkingSlot).starTime(System.currentTimeMillis()).vehicle(vehicle).
                ticketNo(vehicle.getVehicle()+System.currentTimeMillis()).build();
    }
}

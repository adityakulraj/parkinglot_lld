
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter @Setter
public class ParkingLot {
    private String name;
    private List<ParkingFloor> parkingFloors;
    private static ParkingLot parkinglot=null;

    private ParkingLot(String name, List<ParkingFloor> parkingFloors) {
        this.name = name;
        this.parkingFloors = parkingFloors;
    }

    public static ParkingLot getInstance(String name, List<ParkingFloor> parkingFloors) {
        if(parkinglot==null) {
            parkinglot = new ParkingLot(name, parkingFloors);
        }
        return parkinglot;
    }

    public void addFloors(String name, Map<ParkingSlotType, Map<String,ParkingSlot>> parkingslots) {
        ParkingFloor parkingFloor = new ParkingFloor(name,parkingslots);
        parkingFloors.add(parkingFloor);
    }

    public void removeFloors(ParkingFloor parkingFloor) {
        parkingFloors.remove(parkingFloor);
    }

    public Ticket assignTicket(Vehicle vehicle) {
        ParkingSlot parkingSlot = getParkingSlotForVehicleAndPark(vehicle);
        if(parkingSlot==null) return null;
        Ticket parkingTicket = createTicketForSlot(parkingSlot, vehicle);
        return parkingTicket;
    }

    public double scanAndPay(Ticket ticket) {
        long endTime = System.currentTimeMillis();
        ticket.getParkingSlot().removeVehicle(ticket.getVehicle());
            int duration = (int) (endTime - ticket.getStarTime())/1000;
            double price = ticket.getParkingSlot().getParkingSlotType().getPriceForParking(duration);
            return price;


    }

    public Ticket createTicketForSlot(ParkingSlot parkingslot, Vehicle vehicle) {
        return Ticket.createTicket(vehicle, parkingslot);
    }

    private ParkingSlot getParkingSlotForVehicleAndPark(Vehicle vehicle) {
        ParkingSlot parkingSlot=null;
        for(ParkingFloor floor : parkingFloors){
            parkingSlot = floor.getRelevantSlotForVehicleAndPark(vehicle);
            if(parkingSlot!= null) break;
        }
        return parkingSlot;
    }

}

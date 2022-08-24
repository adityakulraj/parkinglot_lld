import java.util.Map;

public class ParkingFloor {
    String name;
    Map<ParkingSlotType, Map<String, ParkingSlot>> parkingSlots;
    public ParkingFloor(String name, Map<ParkingSlotType, Map<String ,ParkingSlot>> parkingSlots ) {
        this.name=name;
        this.parkingSlots=parkingSlots;
    }

    public ParkingSlot getRelevantSlotForVehicleAndPark(Vehicle vehicle) {
        VehicleCategory vehicleCategory = vehicle.getVehicleCategory();
        ParkingSlotType parkingSlotType = pickCorrectSlot(vehicleCategory);
        Map<String,ParkingSlot> relevantParkingSlot = parkingSlots.get(parkingSlotType);
        ParkingSlot slot = null;
        for(String s : relevantParkingSlot.keySet()) {
            if(relevantParkingSlot.get(s).isAvailable()) {
                slot = relevantParkingSlot.get(s);
                slot.addVehicle(vehicle);
                break;
            }
        }

        return slot;
    }

    private ParkingSlotType pickCorrectSlot(VehicleCategory vehicleCategory) {
        if(vehicleCategory.equals(VehicleCategory.Bike)) return ParkingSlotType.TwoWheeler;
        else if(vehicleCategory.equals(VehicleCategory.Hatchback) || vehicleCategory.equals(VehicleCategory.Sedan)) return ParkingSlotType.Compact;
        else if(vehicleCategory.equals(VehicleCategory.SUV)) return ParkingSlotType.Medium;
        else if(vehicleCategory.equals(VehicleCategory.BUs)) return ParkingSlotType.Large;

        return null;
    }


}

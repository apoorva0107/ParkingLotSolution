import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ParkingLotImpl {

    static int totalSlots = 0;
    static ArrayList<Integer> availableSlots = new ArrayList<>();
    static Map<Integer, ParkingTicket> mapSlotAndTicket = new HashMap<>();
    static Map<Integer,ArrayList<String>> mapAgeAndRegistrationNumber = new HashMap<>();
    static Map<String,Integer> mapRegistrationNumberAndSlot = new HashMap<>();

    //Assuming that Create_parking_lot is always the first command, adn will only have one occurrence

    public static void createParkingLot(int slots){
        totalSlots = slots;
        for(int i=1 ; i<=totalSlots ; i++){
            availableSlots.add(i);
        }
        System.out.println("Created parking of " + totalSlots + " slots");
    }

    public static void park(String RegistrationNumber, int age){
        if(totalSlots == 0){
            System.out.println("Error in creating parking lot");
        }
        if(mapSlotAndTicket.size() == totalSlots){
            System.out.println("Parking lot is currently full");
        }
        else if(mapRegistrationNumberAndSlot.containsKey(RegistrationNumber)){
            System.out.println("This vehicle is already present in the parking lot");
        } else {
            Collections.sort(availableSlots);

            ParkingTicket ticket = new ParkingTicket(RegistrationNumber, age);
            int nearestAvailableSlot = availableSlots.get(0);

            mapSlotAndTicket.put(nearestAvailableSlot, ticket);
            mapRegistrationNumberAndSlot.put(RegistrationNumber, nearestAvailableSlot);

            ArrayList<String> registrationNumberList;
            if(mapAgeAndRegistrationNumber.containsKey(age))
            {
                registrationNumberList = mapAgeAndRegistrationNumber.get(age);
            } else{
                registrationNumberList = new ArrayList<>();
            }
            registrationNumberList.add(RegistrationNumber);
            mapAgeAndRegistrationNumber.put(age, registrationNumberList);
            availableSlots.remove(0);
            System.out.println("Car with vehicle registration number \"" + RegistrationNumber + "\" has been parked at slot number " + nearestAvailableSlot);
        }
    }

    public static void fetchSlotNumbersFromDriverAge(int age){
        if(totalSlots == 0){
            System.out.println("Parking lot is not created");
        }
        if(mapAgeAndRegistrationNumber.containsKey(age) && mapAgeAndRegistrationNumber.get(age).size()>0){
            StringBuilder slotNumbers = new StringBuilder();
            for(String registrationNumber: mapAgeAndRegistrationNumber.get(age)){
                slotNumbers.append(mapRegistrationNumberAndSlot.get(registrationNumber)).append(",");
            }
            System.out.println(slotNumbers.substring(0, slotNumbers.length()-1));
        } else{
            System.out.println("No driver present with age " + age);
        }
    }

    public static void fetchSlotNumberFromVehicleRegistrationNumber(String registrationNumber){
        if(totalSlots == 0){
            System.out.println("Parking lot is not created");
        }
        if(mapRegistrationNumberAndSlot.containsKey(registrationNumber)){
            System.out.println(mapRegistrationNumberAndSlot.get(registrationNumber));
        } else{
            System.out.println("The Vehicle with registration number \"" + registrationNumber + "\" not found in Parking Lot");
        }
    }

    public static void leave(int slot){
        if(totalSlots == 0){
            System.out.println("Parking lot is not created");
        }
        else if(totalSlots >= 1){
            ParkingTicket leavingVehicle = mapSlotAndTicket.get(slot);
            if(leavingVehicle == null){
                System.out.println("No vehicle is present in the given slot");
            } else{
                mapSlotAndTicket.remove(slot);
                mapRegistrationNumberAndSlot.remove(leavingVehicle.getRegistrationNumber());

                ArrayList<String> registrationNumberList = mapAgeAndRegistrationNumber.get(leavingVehicle.getDriverAge());

                if(registrationNumberList.contains(leavingVehicle.getRegistrationNumber())){
                    mapAgeAndRegistrationNumber.get(leavingVehicle.getDriverAge()).remove(leavingVehicle.getRegistrationNumber());
                }
                availableSlots.add(slot);
                System.out.println("Slot number " + slot + " vacated, the car with vehicle registration number \"" + leavingVehicle.getRegistrationNumber()
                        + "\" left the space, the driver of the car was of age " + leavingVehicle.getDriverAge());
            }
        } else{
            System.out.println("the parking lot is empty");
        }
    }

    public static void fetchVehicleRegistrationNumbersByAge(int age){
        if(totalSlots == 0){
            System.out.println("The Parking Lot is not Created");
        }
        else if(mapAgeAndRegistrationNumber.containsKey(age) && mapAgeAndRegistrationNumber.get(age).size()>0){
            StringBuilder number = new StringBuilder();

            for(String registrationNumber: mapAgeAndRegistrationNumber.get(age)){
                number.append(registrationNumber).append(",");
            }
            System.out.println(number.substring(0, number.length()-1));
        } else{
            System.out.println("");
        }
    }
}

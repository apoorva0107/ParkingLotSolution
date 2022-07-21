import java.util.Objects;

public class ProcessInput {

    public static void inputProcessor(String input_str) {
        String[] input = input_str.split(" ");
        if(input.length == 0 || input.length == 1 || input.length == 3){
            System.out.println("invalid input command");
        }
        else if(input.length == 2){
            if(Objects.equals(input[0], "Create_parking_lot")){
                ParkingLotImpl.createParkingLot(Integer.parseInt(input[1]));
            }
            else if(Objects.equals(input[0], "Slot_numbers_for_driver_of_age")){
                int age = Integer.valueOf(input[1]);
                ParkingLotImpl.fetchSlotNumbersFromDriverAge(age);
            }
            else if(Objects.equals(input[0], "Slot_number_for_car_with_number")){
                String registrationNumber = input[1];
                ParkingLotImpl.fetchSlotNumberFromVehicleRegistrationNumber(registrationNumber);
            }
            else if(Objects.equals(input[0], "Leave")){
                int slot = Integer.valueOf(input[1]);
                ParkingLotImpl.leave(slot);
            }
            else if(Objects.equals(input[0], "Vehicle_registration_number_for_driver_of_age")){
                int age = Integer.valueOf(input[1]);
                ParkingLotImpl.fetchVehicleRegistrationNumbersByAge(age);
            } else{
                System.out.println("invalid input command");
            }
        }

        //Assuming that the length of vehicle registration number will always be 13
        else if( input.length == 4){
            if (Objects.equals(input[0], "Park") && Objects.equals(input[2], "driver_age") && input[1].length() == 13){
                try{
                    int age = Integer.parseInt(input[3]);
                    String registrationNumber = input[1];
                    ParkingLotImpl.park(registrationNumber, age);
                }
                catch (Exception e){
                    System.out.println("fields missing in statement");
                }
            }
        } else{
            System.out.println("invalid input command");
        }
    }
}

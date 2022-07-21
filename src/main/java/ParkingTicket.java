public class ParkingTicket {

    private String registrationNumber;
    private int driverAge;

    public ParkingTicket(String registrationNumber, int driverAge) {
        this.registrationNumber = registrationNumber;
        this.driverAge = driverAge;
    }

    public int getDriverAge(){
        return this.driverAge;
    }

    public String getRegistrationNumber(){
        return this.registrationNumber;
    }
}

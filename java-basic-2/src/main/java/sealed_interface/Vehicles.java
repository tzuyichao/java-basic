package sealed_interface;

public abstract sealed class Vehicles permits Car, Truck {
    protected final String registrationNumber;

    public Vehicles(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }
}

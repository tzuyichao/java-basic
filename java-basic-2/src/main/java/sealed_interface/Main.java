package sealed_interface;

/**
 * example from Baeldung
 */
public class Main {
    public static void main(String[] args) {
        Vehicles vehicle1 = new Car(10, "CA-100");
        if(vehicle1 instanceof Car car) {
            System.out.println(car.getNumberOfSeats());
        } else if(vehicle1 instanceof Truck truck) {
            System.out.println(truck.getLoadCapacity());
        } else {
            throw new RuntimeException("Unknown instance of vehicle");
        }
    }
}

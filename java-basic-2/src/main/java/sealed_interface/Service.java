package sealed_interface;

public sealed interface Service permits Car, Truck {
    int getMaxServiceIntervalInMonths();

    default int getMaxDistanceBetweenServicesInKilometers() {
        return 100000;
    }
}

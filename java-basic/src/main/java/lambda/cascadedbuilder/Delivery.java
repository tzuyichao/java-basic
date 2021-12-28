package lambda.cascadedbuilder;

import lombok.Data;
import lombok.ToString;

import java.util.function.Consumer;

@ToString
@Data
public class Delivery {
    private String firstname;
    private String lastname;
    private String address;

    public Delivery firstname(String firstname) {
        setFirstname(firstname);
        return this;
    }

    public Delivery lastname(String lastname) {
        setLastname(lastname);
        return this;
    }

    public Delivery address(String address) {
        setAddress(address);
        return this;
    }

    public static Delivery delivery(Consumer<Delivery> parcel) {
        var delivery = new Delivery();
        parcel.accept(delivery);
        return delivery;
    }
}

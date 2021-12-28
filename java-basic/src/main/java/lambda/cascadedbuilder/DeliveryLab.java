package lambda.cascadedbuilder;

public class DeliveryLab {
    public static void main(String[] args) {
        var delivery = Delivery.delivery(d ->
                d.firstname("TzuYi").lastname("Chao").address("No. 999, Java Old man Rd."));

        System.out.println(delivery);
    }
}

package basic;

import java.util.Objects;

public class Asserts {
    public static void notNull(Object subject, String message) {
        if(Objects.isNull(subject)) {
            throw new IllegalArgumentException(message);
        }
    }
}

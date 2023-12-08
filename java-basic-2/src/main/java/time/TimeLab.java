package time;

import java.sql.Timestamp;
import java.time.Instant;

public class TimeLab {
    public static void main(String[] args) {
        Instant now = Instant.now();
        System.out.println(now);
        Timestamp ts = Timestamp.from(now);
        System.out.println(ts);
    }
}

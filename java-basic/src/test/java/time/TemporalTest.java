package time;

import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

public class TemporalTest {

    @Test
    public void testDateToInstant() {
        Date date = new Date();
        Instant instantFromDate = date.toInstant();
        System.out.println(instantFromDate);

        Instant instant = Instant.now();
        Date dateFromInstant = Date.from(instant);
        System.out.println(dateFromInstant);
    }

    @Test
    public void defaultTimeZone() {
        final ZoneId defaultTimeZone = ZoneId.systemDefault();
        System.out.println(defaultTimeZone);
    }
}

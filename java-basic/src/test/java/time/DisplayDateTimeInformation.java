package time;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DisplayDateTimeInformation {
    @Test
    public void testZonedDateTime() {
        LocalDateTime ldt = LocalDateTime.of(2021, Month.NOVEMBER, 10, 8, 21);
        ZonedDateTime au = ldt.atZone(ZoneId.of("Australia/Perth"));
        ZonedDateTime eu = ldt.atZone(ZoneId.of("Europe/Bucharest"));
        ZonedDateTime auPerthArrive = au.plusHours(15).plusMinutes(30);
        System.out.println(ldt);
        System.out.println(au);
        System.out.println(eu);
        System.out.println(auPerthArrive);

    }
}

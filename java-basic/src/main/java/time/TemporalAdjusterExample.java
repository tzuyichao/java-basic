package time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * source: https://howtodoinjava.com/java/date-time/java8-temporal-adjusters/
 */
public class TemporalAdjusterExample {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.parse("2020-05-09");
        List<LocalDate> dates = getPeriodicMeetingDates(localDate, TemporalAdjusters.next(DayOfWeek.MONDAY), 5);
        System.out.println(dates);
    }

    private static List<LocalDate> getPeriodicMeetingDates(final LocalDate startDate, final TemporalAdjuster adjuster, final int numbers) {
        List<LocalDate> res = new ArrayList<>();
        LocalDate localDate = startDate;
        for(int i=0; i<numbers; i++) {
            localDate = localDate.with(adjuster);
            res.add(localDate);
        }

        return res;
    }
}

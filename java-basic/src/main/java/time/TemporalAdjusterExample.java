package time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
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

        LocalDate toBirth = localDate.with(new MoveToBirthDay());
        System.out.println(toBirth);

        TemporalAdjuster nextSevenDay = t -> t.plus(Period.ofDays(7));
        LocalDate sevenDaysLater = localDate.with(nextSevenDay);
        System.out.println(sevenDaysLater);
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

    static class MoveToBirthDay implements TemporalAdjuster {
        @Override
        public Temporal adjustInto(Temporal temporal) {
            return temporal.with(ChronoField.MONTH_OF_YEAR, 1)
                    .with(ChronoField.DAY_OF_MONTH, 11);
        }
    }
}

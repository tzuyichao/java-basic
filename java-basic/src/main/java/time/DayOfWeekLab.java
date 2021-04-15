package time;

import java.time.DayOfWeek;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;

public class DayOfWeekLab {
    public static void main(String[] args) {
        // 之前廣為人知的
        DayOfWeek monday = DayOfWeek.valueOf("monday".toUpperCase());
        System.out.println(monday);

        // TemporalQuery的案例
        TemporalQuery<Boolean> isWorkDayQuery = new TemporalQuery<Boolean>() {
            @Override
            public Boolean queryFrom(TemporalAccessor temporal) {
                System.out.println(temporal);
                if(DayOfWeek.SATURDAY == temporal || DayOfWeek.SUNDAY == temporal) {
                    return Boolean.FALSE;
                }
                return Boolean.TRUE;
            }
        };

        Boolean res = monday.query(isWorkDayQuery);
        System.out.println(String.format("%s is work day? %s", monday, res));
    }
}

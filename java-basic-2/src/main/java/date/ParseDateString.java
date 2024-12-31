package date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class ParseDateString {
    public static void main(String[] args) {
        String dateTime1 = "2024-08-21T17:46:40.523";
        String dateTime2 = "2024-08-21T17:46:40.54";
        String dateTime3 = "2024-08-21T17:46:40.5";
        String dateTime4 = "2024-08-21T17:46:40";

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                .optionalStart()
                .appendFraction(ChronoField.MILLI_OF_SECOND, 1, 3, true)
                .optionalEnd()
                .toFormatter();

        LocalDateTime parsed1 = LocalDateTime.parse(dateTime1, formatter);
        LocalDateTime parsed2 = LocalDateTime.parse(dateTime2, formatter);
        LocalDateTime parsed3 = LocalDateTime.parse(dateTime3, formatter);
        LocalDateTime parsed4 = LocalDateTime.parse(dateTime4, formatter);

        System.out.println(parsed1);
        System.out.println(parsed2);
        System.out.println(parsed3);
        System.out.println(parsed4);
    }
}


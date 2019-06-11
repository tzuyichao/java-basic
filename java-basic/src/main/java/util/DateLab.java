package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateLab {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        System.out.println(sdf.format(new Date()));
        sdf.setTimeZone(TimeZone.getTimeZone("Japan"));
        System.out.println(sdf.format(new Date()));
        sdf.setTimeZone(TimeZone.getTimeZone("GMT")); // ISO8601 Z代表並恰好與協調世界時相同，那麼（不加空格地）在時間最後加一個大寫字母Z。Z是相對協調世界時時間0偏移的代號。
        System.out.println(sdf.format(new Date()));
        System.out.println(new Date());
    }
}

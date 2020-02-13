package concurrent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static String format(Date d) {
        return sdf.format(d);
    }

    public static Date parse(String dateStr) {
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException(dateStr);
        }
    }

    public static void main(String[] args) {
        class FormatTaskThread extends Thread {
            private Date date = null;
            private String expected = null;

            public FormatTaskThread(Date date) {
                this.date = date;
                this.expected = CommonUtil.format(date);
            }

            public void run() {
                while(true) {
                    String str = CommonUtil.format(date);
                    if(!str.equals(expected)) {
                        System.out.println("return " + str + " expected " + expected);
                    }
                }
            }
        }

        FormatTaskThread[] ts = new FormatTaskThread[2];
        for(int i=0; i<ts.length; i++) {
            String expected = "2011-2-" + i + "1";
            ts[i] = new FormatTaskThread(CommonUtil.parse(expected));
        }

        for(int i=0; i<ts.length; i++) {
            ts[i].start();
        }
    }
}

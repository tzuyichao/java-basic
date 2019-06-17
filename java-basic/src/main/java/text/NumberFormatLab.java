package text;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatLab {
    public static void main(String[] args) {
        NumberFormat numberFormat = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);
        System.out.println(numberFormat.format(42_000));
        System.out.println(numberFormat.format(42_000_000));

        NumberFormat percentNumberFormat = NumberFormat.getPercentInstance();
        System.out.println(percentNumberFormat.format(0.51));

        NumberFormat currencyNumberFormat = NumberFormat.getCurrencyInstance(Locale.JAPAN);
        System.out.println(currencyNumberFormat.format(15_000));
    }
}

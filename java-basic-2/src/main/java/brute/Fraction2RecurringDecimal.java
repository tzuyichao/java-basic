package brute;

import java.util.HashMap;
import java.util.Map;

/**
 * 166. Fraction to Recurring Decimal
 * https://leetcode.com/problems/fraction-to-recurring-decimal/
 *
 * Runtime: 3 ms, faster than 54.85% of Java online submissions for Fraction to Recurring Decimal.
 * Memory Usage: 42.4 MB, less than 7.84% of Java online submissions for Fraction to Recurring Decimal.
 */
public class Fraction2RecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        int s1 = numerator >= 0 ? 1 : -1;
        int s2 = denominator >= 0 ? 1 : -1;
        long n = Math.abs((long) numerator);
        long d = Math.abs((long) denominator);
        long q = n / d;
        long rem = n % d;
        StringBuilder res = new StringBuilder();
        res.append(q);
        if (s1 * s2 == -1 && (q > 0 || rem > 0)) {
            res.insert(0, "-");
        }
        if (rem == 0) {
            return res.toString();
        }
        res.append(".");
        var pos = 0;
        Map<Long, Integer> m = new HashMap<>();
        StringBuilder s = new StringBuilder();
        while (rem != 0) {
            if (m.containsKey(rem)) {
                s.insert(m.get(rem), "(");
                res.append(s).append(")");
                return res.toString();
            }
            m.put(rem, pos);
            s.append(Long.toString((rem * 10) / d));
            rem = (rem * 10) % d;
            pos++;
        }
        return res.append(s).toString();
    }
}

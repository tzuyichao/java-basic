package brute;

import java.util.HashMap;
import java.util.Map;

/**
 * 397. Integer Replacement
 * https://leetcode.com/problems/integer-replacement/
 *
 * Runtime: 8 ms, faster than 28.62% of Java online submissions for Integer Replacement.
 * Memory Usage: 39 MB, less than 58.20% of Java online submissions for Integer Replacement.
 */
public class IntegerReplacement {

    public int integerReplacement(int n) {
        if (n == 1) return 0;
        if (n % 2 == 0) return 1 + integerReplacement(n / 2);
        else {
            long t = n;
            return 2 + Math.min(integerReplacement((int)((t + 1) / 2)), integerReplacement((int) ((t - 1) / 2)));
        }
    }
}

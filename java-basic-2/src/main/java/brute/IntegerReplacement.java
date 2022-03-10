package brute;

import java.util.HashMap;
import java.util.Map;

/**
 * 397. Integer Replacement
 * https://leetcode.com/problems/integer-replacement/
 *
 */
public class IntegerReplacement {

    public int integerReplacement(int n) {
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(1, 0);
        dp.put(2, 1);
        var i = 3;
        while(i<=n) {
            if(i % 2 == 0) {
                dp.put(i, 1 + dp.get(i>>1));
            } else {
                dp.put(i, Math.min(dp.get(i-1), dp.get((i+1)>>1) + 1) + 1);
            }
            i++;
        }
        return dp.get(n);
    }
}

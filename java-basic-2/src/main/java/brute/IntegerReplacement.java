package brute;

import java.util.HashMap;
import java.util.Map;

/**
 * 397. Integer Replacement
 * https://leetcode.com/problems/integer-replacement/
 *
 */
public class IntegerReplacement {
    Map<Integer, Integer> dp = new HashMap<>();

    {
        dp.put(1, 0);
        dp.put(2, 1);
    }

    public int integerReplacement(int n) {
        while(!dp.containsKey(n)) {
            if(n % 2 == 0) {
                dp.put(n, 1 + integerReplacement(n>>1));
            } else {
                dp.put(n, 1+ Math.min(integerReplacement((n-1) >> 1) + 1, integerReplacement((n+1)>>1) + 1));
            }
        }
        return dp.get(n);
    }
}

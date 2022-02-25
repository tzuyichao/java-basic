package brute;

/**
 * 134. Gas Station
 * https://leetcode.com/problems/gas-station/
 *
 * Time Limit Exceeded
 *
 * Runtime: 2611 ms, faster than 5.00% of Java online submissions for Gas Station.
 * Memory Usage: 62.2 MB, less than 76.52% of Java online submissions for Gas Station.
 *
 * skip initial gas is zero cases
 */
public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int res = -1;
        var n = gas.length;
        var c = 0;
        for(int i=0; i<n; i++) {
            c = gas[i];
            if(c == 0) {
                continue;
            }
            var w = 0;
            while(w < n) {
                if (c - cost[(i+w)%n] >= 0) {
                    c = c - cost[(i+w)%n] + gas[(i+w+1)%n];
                    w += 1;
                    if(w == n) {
                        return i;
                    }
                } else {
                    break;
                }
            }
        }

        return res;
    }
}

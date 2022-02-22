package brute;

import java.util.Arrays;

/**
 * 983. Minimum Cost For Tickets
 * https://leetcode.com/problems/minimum-cost-for-tickets/
 *
 * Runtime: 8 ms, faster than 9.67% of Java online submissions for Minimum Cost For Tickets.
 * Memory Usage: 41.9 MB, less than 26.46% of Java online submissions for Minimum Cost For Tickets.
 */
public class MinimumCostForTickets {
    public int mincostTickets(int[] days, int[] costs) {
        var dp = new int[days.length+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=1; i<=days.length; i++) {
            dp[i] = Math.min(dp[i], dp[i-1] + costs[0]);
            for(int j=1; j<=i; j++) {
                if(days[j-1] +7 > days[i-1]) {
                    dp[i] = Math.min(dp[i], dp[j-1] + costs[1]);
                }
                if(days[j-1] + 30 > days[i-1]) {
                    dp[i] = Math.min(dp[i], dp[j-1] + costs[2]);
                }
            }
        }
        return dp[days.length];
    }
}

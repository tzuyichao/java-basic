package brute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 322. Coin Change
 * https://leetcode.com/problems/coin-change/
 *
 * Time Limit Exceeded
 *
 * dp version:
 * Runtime: 28 ms, faster than 54.08% of Java online submissions for Coin Change.
 * Memory Usage: 44.7 MB, less than 39.93% of Java online submissions for Coin Change.
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        var dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;

        for(int i=1; i<amount+1; i++) {
            for(int j=0; j<coins.length; j++) {
                if(i - coins[j] >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        if(dp[amount] <= amount) {
            return dp[amount];
        } else {
            return -1;
        }
    }
}

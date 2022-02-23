package brute;

/**
 * 91. Decode Ways
 * https://leetcode.com/problems/decode-ways/
 *
 * Runtime: 1 ms, faster than 95.53% of Java online submissions for Decode Ways.
 * Memory Usage: 42 MB, less than 30.11% of Java online submissions for Decode Ways.
 */
public class DecodeWays {
    public int numDecodings(String s) {
        if(s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }
        var n = s.length();
        var dp = new int[n+1];
        dp[0] = 1;
        for(int i=1; i<dp.length; i++) {
            dp[i] = s.charAt(i-1) == '0'?0:dp[i-1];
            if(i > 1 && (s.charAt(i-2) == '1'|| s.charAt(i-2) == '2' && s.charAt(i-1) <= '6')) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }
}

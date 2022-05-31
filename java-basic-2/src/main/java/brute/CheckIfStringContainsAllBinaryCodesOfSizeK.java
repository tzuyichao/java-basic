package brute;

/**
 * 1461. Check If a String Contains All Binary Codes of Size K
 * https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/
 *
 * https://dev.to/seanpgallivan/solution-check-if-a-string-contains-all-binary-codes-of-size-k-26gg#idea
 */
public class CheckIfStringContainsAllBinaryCodesOfSizeK {
    public boolean hasAllCodes(String s, int k) {
        int len = s.length();
        int count = 1 << k;
        if(len < k) {
            return false;
        }
        int num = k > 1 ? Integer.parseInt(s.substring(len - k + 1), 2) << 1 : 0;
        boolean[] seen = new boolean[count];
        for (int i = len - k; i >= 0; i--) {
            num = (((s.charAt(i) - '0') << k) + num) >> 1;
            if (!seen[num]) {
                seen[num] = true;
                count--;
            }
            if (count == 0) {
                return true;
            }
            if (i < count) {
                return false;
            }
        }

        return false;
    }
}

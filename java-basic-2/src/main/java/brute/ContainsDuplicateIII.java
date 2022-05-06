package brute;

import java.math.BigDecimal;

/**
 * 220. Contains Duplicate III
 * https://leetcode.com/problems/contains-duplicate-iii/
 *
 * Time Limit Exceeded
 */
public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        var n = nums.length;
        var res = false;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(j != i && Math.abs(i-j) <= k) {
                    BigDecimal iv = new BigDecimal(nums[i]);
                    BigDecimal jv = new BigDecimal(nums[j]);
                    if(iv.subtract(jv).abs().compareTo(new BigDecimal(t)) <= 0) {
                        return true;
                    }
                }
            }
        }
        return res;
    }
}

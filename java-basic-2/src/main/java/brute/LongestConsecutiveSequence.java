package brute;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 128. Longest Consecutive Sequence
 * https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * Time Limit Exceeded
 *
 * Time Limit Exceeded
 *
 * Runtime: 27 ms, faster than 76.15% of Java online submissions for Longest Consecutive Sequence.
 * Memory Usage: 60.8 MB, less than 50.27% of Java online submissions for Longest Consecutive Sequence.
 */
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        var store = new HashSet<Integer>();
        for(var num: nums) {
            store.add(num);
        }
        int res = 0;
        for(var num: store) {
            if(!store.contains(num+1)) {
                var c = num;
                var cConsecutive = 1;
                while(store.contains(c-1)) {
                    c -= 1;
                    cConsecutive += 1;
                }
                res = Math.max(res, cConsecutive);
            }
        }
        return res;
    }
}

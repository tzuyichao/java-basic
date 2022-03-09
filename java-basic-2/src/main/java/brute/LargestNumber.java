package brute;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 179. Largest Number
 * https://leetcode.com/problems/largest-number/
 *
 * Runtime: 24 ms, faster than 8.01% of Java online submissions for Largest Number.
 * Memory Usage: 44.6 MB, less than 17.08% of Java online submissions for Largest Number.
 *
 * https://medium.com/@ChYuan/leetcode-no-179-largest-number-%E5%BF%83%E5%BE%97-medium-e578b1d9b89e
 */
public class LargestNumber {

    public String largestNumber(int[] nums) {
        var res = Arrays.stream(nums)
                .mapToObj(Integer::toString)
                .sorted((a, b) -> -(a+b).compareTo((b+a)))
                .collect(Collectors.joining());

        return res.replaceFirst("^0+(?!$)", "");
    }
}

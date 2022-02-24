package brute;

import java.util.HashMap;
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
 */
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        var connect = new HashMap<Integer, Integer>();
        for(var num: nums) {
            connect.put(num, num);
        }

        for(var num: connect.keySet()) {
            if(connect.containsKey(num+1) && connect.get(num+1) == num+1) {
                Integer p = connect.get(num+1);
                var idList = connect.entrySet().stream().filter(elem -> elem.getValue()==p).map(Map.Entry::getKey).collect(Collectors.toList());
                for(int id: idList) {
                    connect.put(id, connect.get(num));
                }
            }
        }

        long max = connect.values().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).values().stream().max(Long::compareTo).get();

        return (int)max;
    }
}

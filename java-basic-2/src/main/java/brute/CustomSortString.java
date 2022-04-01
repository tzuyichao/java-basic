package brute;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 791. Custom Sort String
 * https://leetcode.com/problems/custom-sort-string/
 *
 * order -> Mapping Table
 * encode s with mapping table
 * sort s'
 * decode s' to res
 * 
 * Runtime: 5 ms, faster than 23.06% of Java online submissions for Custom Sort String.
 * Memory Usage: 43.2 MB, less than 6.06% of Java online submissions for Custom Sort String.
 *
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Custom Sort String.
 * Memory Usage: 40.4 MB, less than 85.99% of Java online submissions for Custom Sort String.
 */
public class CustomSortString {
    public String customSortString(String order, String s) {
        int[] count = new int[26];
        for(char c: s.toCharArray()) {
            ++count[c - 'a'];
        }
        StringBuilder res = new StringBuilder();
        for(char c: order.toCharArray()) {
            while(count[c - 'a']-- > 0) {
                res.append(c);
            }
        }
        for(char c='a'; c<='z'; ++c) {
            while(count[c - 'a']-- > 0) {
                res.append(c);
            }
        }
        return res.toString();
    }
}

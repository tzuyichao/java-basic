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
 * Runtime: 5 ms, faster than 23.06% of Java online submissions for Custom Sort String.
 * Memory Usage: 43.2 MB, less than 6.06% of Java online submissions for Custom Sort String.
 * 
 * order -> Mapping Table
 * encode s with mapping table
 * sort s'
 * decode s' to res
 */
public class CustomSortString {
    class CustomComparator implements Comparator<Character> {
        private Map<Character, Integer> encodeTable;
        CustomComparator(String order) {
            encodeTable = new HashMap<>();
            var n = order.length();
            for(int i=0; i<n; i++) {
                Character c = order.charAt(i);
                encodeTable.put(c, i);
            }
        }

        @Override
        public int compare(Character o1, Character o2) {
            return encodeTable.getOrDefault(o1, Integer.MAX_VALUE).compareTo(encodeTable.getOrDefault(o2, Integer.MAX_VALUE));
        }
    }
    public String customSortString(String order, String s) {
        CustomComparator comparator = new CustomComparator(order);
        char[] chars = s.toCharArray();
        Character[] chs = new Character[chars.length];
        for(int i=0; i<chars.length; i++) {
            chs[i] = Character.valueOf(chars[i]);
        }
        Arrays.sort(chs, comparator);
        for(int i=0; i<chars.length; i++) {
            chars[i] = chs[i].charValue();
        }
        return new String(chars);
    }
}

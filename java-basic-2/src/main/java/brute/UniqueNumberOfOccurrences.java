package brute;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 1207. Unique Number of Occurrences
 * https://leetcode.com/problems/unique-number-of-occurrences/
 * 
 * Runtime: 2 ms, faster than 75.91% of Java online submissions for Unique Number of Occurrences.
 * Memory Usage: 42.6 MB, less than 9.42% of Java online submissions for Unique Number of Occurrences.
 */
public class UniqueNumberOfOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        var db = new HashMap<Integer, Integer>();

        for(var elem: arr) {
            if(db.containsKey(elem)) {
                db.put(elem, db.get(elem) + 1);
            } else {
                db.put(elem, 1);
            }
        }

        var check = new HashSet<Integer>();
        for(var value : db.values()) {
            if(!check.add(value)) {
                return false;
            }
        }
        return true;
    }
}

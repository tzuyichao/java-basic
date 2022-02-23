package brute;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. Insert Interval
 * https://leetcode.com/problems/insert-interval
 *
 * Runtime: 1 ms, faster than 99.23% of Java online submissions for Insert Interval.
 * Memory Usage: 44.4 MB, less than 73.65% of Java online submissions for Insert Interval.
 */
public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        var n = intervals.length;
        var c = 0;
        var res = new ArrayList<int[]>();
        // first part: before newInterval and no overlap
        while(c < n && intervals[c][1] < newInterval[0]) {
            res.add(intervals[c]);
            c++;
        }
        // second part: has overlap with newInterval, change newInterval index 0 and 1 fit requirement
        while(c < n && intervals[c][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[c][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[c][1]);
            c++;
        }
        res.add(newInterval);
        // third part: after newInterval and no overlap
        while(c < n) {
            res.add(intervals[c]);
            c++;
        }

        return res.toArray(new int[][] {});
    }
}

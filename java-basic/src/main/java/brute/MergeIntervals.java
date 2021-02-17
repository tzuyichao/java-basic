package brute;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * = 56
 * v1:
 * Runtime: 915 ms, faster than 5.83% of Java online submissions for Merge Intervals.
 * Memory Usage: 41.4 MB, less than 86.51% of Java online submissions for Merge Intervals.
 */
public class MergeIntervals {
    public int[] merge(int[] a, int[] b) {
        if(a[1] >= b[0] && a[1] <= b[1]) {
            if(a[0] < b[0]) {
                if(a[1] < b[1]) {
                    return new int[]{a[0], b[1]};
                } else {
                    return new int[]{a[0], a[1]};
                }
            } else {
                if(a[1] < b[1]) {
                    return new int[]{b[0], b[1]};
                } else {
                    return new int[]{b[0], a[1]};
                }
            }
        } else if(a[0] >= b[0] && a[0] <= b[1]) {
            if(a[1] < b[1]) {
                return new int[]{b[0], b[1]};
            } else {
                return new int[]{b[0], a[1]};
            }

        } else {
            return null;
        }
    }

    public boolean hasMergeable(int[][] intervals, int target) {
        for(int i=0; i<intervals.length; i++) {
            if(i != target) {
                if(mergeable(intervals[target], intervals[i])) {
                    // merge
                    intervals[target] = merge(intervals[target], intervals[i]);
                    // remove i
                    intervals[i] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean mergeable(int[] a, int[] b) {
        if(a == null || b == null) {
            return false;
        }
        if(a[1] >= b[0] && a[1] <= b[1]) {
            return true;
        }
        if(a[0] >= b[0] && a[0] <= b[1]) {
            return true;
        }
        return false;
    }

    public boolean mergeable(int[][] intervals) {
        for(int i=0; i<intervals.length; i++) {
            for(int j=0; j<intervals.length; j++) {
                if(i != j) {
                    if(mergeable(intervals[i], intervals[j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void doMerge(int[][] intervals) {
        if(!mergeable(intervals)) {
            return;
        }
        while(mergeable(intervals)) {
            for (int i = 0; i < intervals.length; i++) {
                if (hasMergeable(intervals, i) == true) {
                    System.out.println("Do merged");
                }
            }
        }
    }

    public int[][] merge(int[][] intervals) {
        boolean mergeable = mergeable(intervals);
        System.out.println("mergeable:" + mergeable);
        doMerge(intervals);
        List<int[]> remove = Stream.of(intervals)
                .filter(item -> item != null)
                .collect(Collectors.toList());
        int[][] result = new int[remove.size()][];
        for(int i=0; i<result.length; i++) {
            result[i] = remove.get(i);
        }
        return result;
    }
}

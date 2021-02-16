package brute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * = 16
 *
 * Runtime: 18 ms, faster than 10.37% of Java online submissions for 3Sum Closest.
 * Memory Usage: 40 MB, less than 5.23% of Java online submissions for 3Sum Closest.
 */
public class ThreeSumClosest {
    static class Record {
        public final int idx1;
        public final int idx2;
        public final int idx3;
        public final int sum;
        public final int diff;

        public Record(int idx1, int idx2, int idx3, int sum, int diff) {
            this.idx1 = idx1;
            this.idx2 = idx2;
            this.idx3 = idx3;
            this.sum = sum;
            this.diff = diff;
        }

        public int getDiff() {
            return diff;
        }
    }

    public int threeSumClosest(int[] nums, int target) {
        if(nums.length == 3) {
            return nums[0] + nums[1] + nums[2];
        }
        List<Record> records = new ArrayList<>();
        Arrays.sort(nums);
        for(int first=0; first<nums.length-2; first++) {
            int second = first + 1;
            int third = nums.length - 1;
            while(second < third) {
                int sum = nums[first] + nums[second] + nums[third];
                int diff = target - sum;
                if(diff == 0) {
                    return target;
                } else {
                    Record record = new Record(first, second, third, sum, Math.abs(sum - target));
                    records.add(record);
                    if(sum < target) {
                        second++;
                    } else {
                        third--;
                    }
                }
            }
        }
        records.sort(Comparator.comparingInt(Record::getDiff));
        return records.get(0).sum;
    }
}

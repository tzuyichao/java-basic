package brute;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SummaryRanges {
    static class Range {
        int start;
        int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            if(start == end) {
                result.append(start);
            } else if(end > start) {
                result.append(start).append("->").append(end);
            }
            return result.toString();
        }
    }

    public List<String> summaryRanges(int[] nums) {
        List<Range> ranges = new ArrayList<>();
        if(nums.length > 0) {
            int start = nums[0];
            int previous = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (previous + 1 == nums[i]) {
                    previous = nums[i];
                } else {
                    ranges.add(new Range(start, previous));
                    start = nums[i];
                    previous = nums[i];
                }
            }
            if(ranges.size() == 0 || ranges.get(ranges.size()-1).end != previous) {
                ranges.add(new Range(start, previous));
            }
        }
        return ranges.stream().map(Range::toString).collect(Collectors.toList());
    }
}

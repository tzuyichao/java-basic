package brute;

import java.util.*;
import java.util.stream.IntStream;

public class SortTheJumbledNumbers {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        List<int[]> mapped = new ArrayList<>();

        IntStream.range(0, nums.length).forEach(val -> {
            int[] pair = new int[2];
            int original = nums[val];
            int result = 0;
            int multiplier = 1;
            if(original == 0) {
                pair[0] = mapping[original];;
                pair[1] = val;
                mapped.add(pair);
            } else {
                while (original > 0) {
                    int digit = original % 10;
                    int mappedDigit = mapping[digit];
                    result += mappedDigit * multiplier;
                    multiplier *= 10;
                    original /= 10;
                }
                pair[0] = result;
                pair[1] = val;
                mapped.add(pair);
            }
        });

        mapped.forEach(pair -> System.out.println(pair[0] + " " + pair[1]));

        Collections.sort(mapped, Comparator.comparingInt(a -> a[0]));

        int[] ans = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            ans[i] = nums[mapped.get(i)[1]];
        }
        return ans;
    }
}

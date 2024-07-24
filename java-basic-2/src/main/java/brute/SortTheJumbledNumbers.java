package brute;

import java.util.Arrays;

public class SortTheJumbledNumbers {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        int[] mapped = Arrays.stream(nums).map(val -> {
            int result = 0;
            int multiplier = 1;
            if(val == 0) {
                return mapping[0];
            }
            while (val > 0) {
                int digit = val % 10;
                int mappedDigit = mapping[digit];
                result += mappedDigit * multiplier;
                multiplier *= 10;
                val /= 10;
            }
            return result;
        }).toArray();

        for (int n : mapped) {
            System.out.print(n + " ");
        }

        int len = mapped.length;
        for(int i = 1; i < len; i++) {
            int val = mapped[i];
            int nval = nums[i];
            int j = i - 1;

            while(j >= 0 && val < mapped[j]) {
                mapped[j+1] = mapped[j];
                nums[j+1] = nums[j];
                j--;
            }
            mapped[j+1] = val;
            nums[j+1] = nval;
        }
        return nums;
    }
}

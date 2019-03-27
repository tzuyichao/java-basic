package basic;

import java.util.Arrays;

public class basic {
    public static void main(String[] args) throws SolutionNotFoundException {
        int[] nums = new int[] {9, 2, 5, 7, 11};
        int target = 9;

        int[] result = twoSum(nums, target);
        Arrays.stream(result).forEach(System.out::println);
    }

    public static int[] twoSum(int[] nums, int target) throws SolutionNotFoundException {
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[j] == target - nums[i]) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {-1, -1};
    }
}

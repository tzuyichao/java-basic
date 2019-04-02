package basic;

import java.util.Arrays;

public class basic {
    public static void main(String[] args) throws SolutionNotFoundException {
        int[] nums = new int[] {9, 2, 5, 7, 11};
        int target = 9;

        int[] result = twoSum(nums, target);
        Arrays.stream(result).forEach(System.out::println);

        // String#indexOf()
        String path = "/2/1/50/129";
        System.out.println(path.substring(path.indexOf("50") + "50".length()));
        System.out.println(path.substring("/2/1/50".length()));
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
    public static Integer basicMath(String op, int v1, int v2)
    {
        switch (op) {
            case "+":
                return v1 + v2;
            case "-":
                return v1 - v2;
            case "*":
                return v1 * v2;
            case "/":
                return v1 / v2;
        }
        return 0;
    }
}

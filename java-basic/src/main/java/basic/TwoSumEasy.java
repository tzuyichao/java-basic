package basic;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public class TwoSumEasy {
    @Data
    static class Result {
        int a, b;

        public Result(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    static Result solve(int[] arr, int target) {
        Map<Integer, Integer> visited = new HashMap<>();
        for(int i=0; i<arr.length; i++) {
            int sub = target - arr[i];
            if(visited.containsKey(sub)) {
                return new Result(visited.get(sub), i);
            }
            visited.put(arr[i], i);
        }
        throw new IllegalArgumentException("no answer");
    }

    public static void main(String[] args) {
        int[] arr = new int[] {2, 7, 11, 15, 1};
        System.out.println(solve(arr, 3));
    }
}

package brute;

import java.util.HashMap;
import java.util.Map;

/**
 * = 167
 */
public class TwoSum2InputArrayIsSorted {
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> store = new HashMap<>();
        for(int i=0; i<numbers.length; i++) {
            int sub = target - numbers[i];
            if(store.containsKey(sub)) {
                return new int[] {store.get(sub)+1, i+1};
            } else {
                store.put(numbers[i], i);
            }
        }
        return new int[] {0, 1};
    }
}

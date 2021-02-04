package brute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * = 448
 */
public class FindAllNumbersDisappearedInArray {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        if(nums.length == 0) {
            return Collections.EMPTY_LIST;
        }
        List<Integer> result = new ArrayList<>();
        boolean[] checker = new boolean[nums.length];
        for(int i=0; i<nums.length; i++) {
            checker[nums[i]-1] = true;
        }
        for(int i=0; i<checker.length; i++) {
            if(checker[i] == false) {
                result.add(i+1);
            }
        }
        return result;
    }
}

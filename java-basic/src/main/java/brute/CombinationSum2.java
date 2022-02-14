package brute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 40. Combination Sum II
 * [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
 * 27
 * Time Limit Exceeded
 */
public class CombinationSum2 {
    private void findResult(int[] candidates, int target, int currIdx, int rightIdx, List<Integer> current, List<List<Integer>> collector) {
        int sum = current.stream().reduce(0, Integer::sum);
        if(target == sum) {
            if(!collector.contains(current)) {
                collector.add(current);
            }
        } else if (target > sum) {
            for(int i=currIdx+1; i<rightIdx; i++) {
                List<Integer> c = new ArrayList<>(current);
                c.add(candidates[i]);
                findResult(candidates, target, i, rightIdx, c, collector);
            }
        }
        return;
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        int l = candidates.length;
        int r = candidates.length;
        for(int i=l-1; i>0; i--) {
            if(candidates[i] <= target) {
                r = i+1;
                break;
            }
        }
        List<List<Integer>> result = new ArrayList<>();

        for(int i=0; i<r; i++) {
            List<Integer> current = new ArrayList<>();
            current.add(candidates[i]);
            findResult(candidates, target, i, r, current, result);
        }

        return result;
    }
}

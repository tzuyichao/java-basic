package brute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 40. Combination Sum II
 * [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
 * 27
 * Time Limit Exceeded
 *
 * Runtime: 6 ms, faster than 50.67% of Java online submissions for Combination Sum II.
 * Memory Usage: 44.5 MB, less than 8.49% of Java online submissions for Combination Sum II.
 */
public class CombinationSum2 {
    private void findResult(int[] candidates, int target, int currIdx, List<Integer> current, List<List<Integer>> collector) {
        if(target < 0) {
            return;
        }
        if(target == 0) {
            collector.add(current);
        }
        for(int i=currIdx; i<candidates.length; i++) {
            if(i > currIdx && candidates[i] == candidates[i-1]) {
                continue;
            }
            if(target - candidates[i] >= 0) {
                List<Integer> c = new ArrayList<>(current);
                c.add(candidates[i]);
                findResult(candidates, target-candidates[i], i+1, c, collector);
            }
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        findResult(candidates, target, 0, current, result);

        return result;
    }
}

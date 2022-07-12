package brute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> rec = new ArrayList<>();
        helper(candidates, res, rec, target, 0);
        return res;
    }

    public void helper(int[] candidates, List<List<Integer>> res, List<Integer> rec, int remain, int pos) {
        if(remain == 0) {
            res.add(new ArrayList<>(rec));
            return;
        }
        for(int i=pos; i<candidates.length; i++) {
            remain -= candidates[i];
            if(remain >= 0) {
                rec.add(candidates[i]);
                helper(candidates, res, rec, remain, i+1);
                rec.remove(rec.size()-1);
            }
            remain += candidates[i];

            while((i+1) != candidates.length && candidates[i+1] == candidates[i])
                i++;
        }
    }
}

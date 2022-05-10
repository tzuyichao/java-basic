package brute;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. Combination Sum III
 * https://leetcode.com/problems/combination-sum-iii/
 *
 * ref: https://zxi.mytechroad.com/blog/searching/leetcode-216-combination-sum-iii/
 */
public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        dfs(k, n, 1, c, res);
        return res;
    }

    private void dfs(int k, int n, int s, List<Integer> current, List<List<Integer>> res) {
        if(k == 0) {
            if(n == 0) {
                res.add(current);
                return;
            }
        }
        for(int i=s; i<=9; i++) {
            if(i > n) {
                return;
            }
            List<Integer> c = new ArrayList<>(current);
            c.add(i);
            dfs(k-1, n-i, i+1, c, res);
        }
    }
}

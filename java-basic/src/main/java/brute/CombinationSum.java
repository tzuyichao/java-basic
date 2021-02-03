package brute;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * = 39
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> candidate = new ArrayList<>();
        for(int i=0; i<candidates.length; i++) {
            if(candidates[i] == target) {
                result.add(List.of(candidates[i]));
            }
            if(candidates[i] < target) {
                candidate.add(candidates[i]);
                if(target % candidates[i] == 0) {
                    List<Integer> r = new ArrayList<>();
                    for(int c=0; c<target/candidates[i]; c++) {
                        r.add(candidates[i]);
                    }
                    result.add(r);
                }
            }
        }

        Map<Integer, Integer> store = new HashMap<>();
        for(int i=0; i<candidate.size(); i++) {
            int sub = target - candidate.get(i);
            if(store.containsKey(sub)) {
                List<Integer> r = new ArrayList<>();
                r.add(candidate.get(i));
                r.add(sub);
                result.add(r);
            } else {
                store.put(candidate.get(i), i);
            }
        }

        return result;
    }
}

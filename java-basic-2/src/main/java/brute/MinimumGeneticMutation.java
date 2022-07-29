package brute;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * from: https://alanzhan.dev/post/2022-06-28-leetcode-433/
 */
public class MinimumGeneticMutation {
    public int minMutation(String start, String end, String[] bank) {
        Queue<String> queue = new LinkedList<>();
        Map<String, Boolean> visited = new HashMap<>();
        if(contains(bank, end) && start.equals(end)) {
            return -1;
        }
        queue.offer(start);
        visited.put(start, Boolean.TRUE);
        int res = 0;
        while(!queue.isEmpty()) {
            int len = queue.size();
            for(int i=0; i<len; i++) {
                String curr = queue.poll();
                if(curr.equals(end)) {
                    return res;
                }

                for(String gene: bank) {
                    if(canMutate(gene, curr) && !visited.getOrDefault(gene, Boolean.FALSE)) {
                        queue.offer(gene);
                        visited.put(gene, Boolean.TRUE);
                    }
                }
            }
            res++;
        }
        return -1;
    }

    private boolean canMutate(String gene1, String gene2) {
        int counter = 0;
        for(int i=0; i<gene1.length(); i++) {
            if(gene1.charAt(i) != gene2.charAt(i)) {
                counter++;
            }
        }
        return counter == 1;
    }

    private boolean contains(String[] bank, String target) {
        for(String gene : bank) {
            if(gene.equals(target)) {
                return true;
            }
        }
        return false;
    }
}

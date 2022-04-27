package brute;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 1202. Smallest String With Swaps
 * https://leetcode.com/problems/smallest-string-with-swaps/
 *
 * Runtime: 71 ms, faster than 50.55% of Java online submissions for Smallest String With Swaps.
 * Memory Usage: 106.9 MB, less than 16.44% of Java online submissions for Smallest String With Swaps.
 * 
 * https://zxi.mytechroad.com/blog/graph/leetcode-1202-smallest-string-with-swaps/
 */
public class SmallestStringWithSwaps {
    private int find(int[] data, int x) {
        if(data[x] != x) {
            data[x] = find(data, data[x]);
        }
        return data[x];
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        int[] parent = new int[n];
        for(int i=0; i<n; i++) {
            parent[i] = i;
        }
        for(var pair: pairs) {
            int xRoot = find(parent, pair.get(0));
            parent[xRoot] = find(parent, pair.get(1));
            parent[pair.get(0)] = find(parent, pair.get(1));
        }

        List<Queue<Character>> ss = new ArrayList<>();
        for(int i=0; i<n; i++) {
            ss.add(new LinkedList<>());
        }
        for(int i=0; i<n; i++) {
            int id = find(parent, i);
            ss.get(id).add(s.charAt(i));
        }
        for(int i=0; i<n; i++) {
            if(!ss.get(i).isEmpty()) {
                ss.set(i, ss.get(i).stream().sorted().collect(Collectors.toCollection(LinkedList::new)));
            }
        }

        StringBuilder res = new StringBuilder();

        for(int i=0; i<n; i++) {
            int id = find(parent, i);
            res.append(ss.get(id).poll());
        }

        return res.toString();
    }

    public static void main(String[] args) {
        var solver = new SmallestStringWithSwaps();
        String result = solver.smallestStringWithSwaps("dcab", List.of(List.of(0, 3), List.of(1, 2), List.of(0, 2)));
        System.out.println(result);
    }
}

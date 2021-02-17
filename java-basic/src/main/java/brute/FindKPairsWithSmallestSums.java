package brute;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * = 373
 * try and error against testcases version:
 * Runtime: 721 ms, faster than 5.05% of Java online submissions for Find K Pairs with Smallest Sums.
 * Memory Usage: 47.5 MB, less than 25.32% of Java online submissions for Find K Pairs with Smallest Sums.
 */
public class FindKPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        Comparator<List<Integer>> c = (o1, o2) -> {
            return Integer.compare(o1.stream().reduce(0, (a, b) -> a+b).intValue(), o2.stream().reduce(0, (a, b) -> a+b).intValue());
        };
        if(k * 10 < nums1.length * nums2.length) {
            int idx1 = -1;
            for (int i = 0; i < nums1.length; i++) {
                if (nums1[i] > 0) {
                    if (i != 0) {
                        idx1 = i;
                    }
                    break;
                }
            }

            int idx2 = -1;
            for (int i = 0; i < nums2.length; i++) {
                if (nums2[i] > 0) {
                    if (i != 0) {
                        idx2 = i;
                    }
                    break;
                }
            }

            if (idx1 != -1 && idx2 != -1) {
                if((idx1) * (idx2) > k) {
                    nums1 = Arrays.copyOf(nums1, idx1);
                    nums2 = Arrays.copyOf(nums2, idx2);
                }
            }
        }
        PriorityQueue<List<Integer>> priorityQueue = new PriorityQueue(k, c);

        for(int i=0; i<nums1.length; i++) {
            for(int j=0; j<nums2.length; j++) {
                boolean inserted = priorityQueue.add(List.of(nums1[i], nums2[j]));
                if(inserted) {
                    priorityQueue.stream().sorted(c);
                }
            }
        }

        return priorityQueue.stream().sorted(c).limit(k).collect(Collectors.toList());
        // Time Limit Exceeded
        /*
        Comparator<List<Integer>> c = (o1, o2) -> {
            return Integer.compare(o1.stream().reduce(0, (a, b) -> a+b).intValue(), o2.stream().reduce(0, (a, b) -> a+b).intValue());
        };
        PriorityQueue<List<Integer>> priorityQueue = new PriorityQueue(k, c);

        for(int i=0; i<nums1.length; i++) {
            for(int j=0; j<nums2.length; j++) {
                priorityQueue.add(List.of(nums1[i], nums2[j]));
            }
        }

        return priorityQueue.stream().sorted(c).limit(k).collect(Collectors.toList());
        */
        // Time Limit Exceeded
        /*
        List<List<Integer>> candidates = new ArrayList<>();
        for(int i=0; i<nums1.length; i++) {
            for(int j=0; j<nums2.length; j++) {
                candidates.add(List.of(nums1[i], nums2[j]));
            }
        }

        return candidates.stream().sorted((o1, o2) -> {
            return o1.stream().reduce(0, (a, b) -> a+b).compareTo(o2.stream().reduce(0, (a, b) -> a+b));}).limit(k).collect(Collectors.toList());
         */
    }
}

package brute;

import java.util.ArrayList;
import java.util.List;

/**
 * 658. Find K Closest Elements
 * https://leetcode.com/problems/find-k-closest-elements/
 *
 * Runtime: 10 ms, faster than 38.32% of Java online submissions for Find K Closest Elements.
 * Memory Usage: 43.9 MB, less than 93.48% of Java online submissions for Find K Closest Elements.
 */
public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        var i = 0;
        var j = arr.length-k;

        while(i < j) {
            int mid = i + (j-i>>1);
            if(x - arr[mid] > arr[mid+k] - x) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }
        System.out.println(i + ":" + j + "|" + arr[i]);
        var res = new ArrayList<Integer>();
        for(int b = i; b<i+k; b++) {
            res.add(arr[b]);
        }
        return res;
    }
}

package brute;

import java.util.Arrays;

/**
 * 923. 3Sum With Multiplicity
 * https://leetcode.com/problems/3sum-with-multiplicity/
 *
 * Runtime: 54 ms, faster than 43.15% of Java online submissions for 3Sum With Multiplicity.
 * Memory Usage: 44.1 MB, less than 55.65% of Java online submissions for 3Sum With Multiplicity.
 */
public class ThreeSumWithMultiplicity {
    public int threeSumMulti(int[] arr, int target) {
        if(null == arr || arr.length < 3) {
            return 0;
        }
        final int M = 1000000007;
        int res = 0;
        Arrays.sort(arr);
        int n = arr.length;

        for(int i=0; i<n-2; i++) {
            int t = target - arr[i];
            int j = i + 1;
            int k = n - 1;
            while(j < k) {
                if(arr[j] + arr[k] < t) {
                    j++;
                } else if(arr[j] + arr[k] > t) {
                    k--;
                } else if(arr[j] != arr[k]) {
                    int left = 1;
                    int right = 1;
                    while(j < k-1 && arr[j] == arr[j+1]) {
                        j++;
                        left++;
                    }
                    while(j+1 < k && arr[k] == arr[k-1]) {
                        k--;
                        right++;
                    }
                    res += left * right;
                    res %= M;
                    if(j+1 == k) {
                        break;
                    } else {
                        j++;
                        k--;
                    }
                } else {
                    res += (k-j) * (k-j+1) / 2;
                    res %= M;
                    break;
                }
            }
        }
        return res;
    }
}

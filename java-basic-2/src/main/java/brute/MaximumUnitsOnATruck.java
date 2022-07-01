package brute;

import java.util.Arrays;

/**
 * 1710. Maximum Units on a Truck
 * https://leetcode.com/problems/maximum-units-on-a-truck/
 *
 */
public class MaximumUnitsOnATruck {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int res = 0;

        Arrays.sort(boxTypes, (a, b) -> {
            if(a[1] != b[1]) {
                return -Integer.compare(a[1], b[1]);
            } else {
                return -Integer.compare(a[0], b[0]);
            }
        });

        for(int[] boxType:boxTypes) {
            if(truckSize > boxType[0]) {
                truckSize -= boxType[0];
                res += boxType[0] * boxType[1];
            } else {
                res += truckSize * boxType[1];
                break;
            }
        }

        return res;
    }
}

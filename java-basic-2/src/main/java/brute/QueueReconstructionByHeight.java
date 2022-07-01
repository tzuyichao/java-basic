package brute;

import java.util.Arrays;

/**
 * 406. Queue Reconstruction by Height
 * https://leetcode.com/problems/queue-reconstruction-by-height/
 */
public class QueueReconstructionByHeight {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (int[] a, int[] b) -> {
            if(a[0] == b[0]) {
                return a[1] - b[1];
            } else {
                return b[0] - a[0];
            }
        });
        for(int i=0; i< people.length; i++) {
            System.out.println(people[i][0] + ":" + people[i][1]);
        }

        int[][] res = new int[people.length][2];
        return res;
    }
}

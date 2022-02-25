package brute;

import java.util.Arrays;

/**
 * 833. Find And Replace in String
 * https://leetcode.com/problems/find-and-replace-in-string/
 *
 * Runtime: 2 ms, faster than 84.16% of Java online submissions for Find And Replace in String.
 * Memory Usage: 43.8 MB, less than 10.63% of Java online submissions for Find And Replace in String.
 *
 * MEMO: beware this problem description do not assure indices array's elements order.
 */
public class FindAndReplaceInString {
    public int idxOf(int[] indices, int val) {
        for(int i=0; i<indices.length; i++) {
            if(indices[i] == val) {
                return i;
            }
        }
        return -1;
    }

    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        var str = new StringBuilder(s);
        var n = indices.length;
        int[] newIndices = new int[indices.length];
        System.arraycopy(indices, 0, newIndices, 0, n);
        Arrays.sort(newIndices);
        int gap = 0;
        for(int i=0; i<n; i++) {
            var val = newIndices[i];
            var idx = idxOf(indices, val);
            var len = sources[idx].length();
            if(sources[idx].equals(str.substring(indices[idx] + gap, indices[idx]+gap+len))) {
                str.replace(indices[idx] + gap, indices[idx]+gap+len, targets[idx]);
                gap += (targets[idx].length() - sources[idx].length());
            }
        }

        return str.toString();
    }
}

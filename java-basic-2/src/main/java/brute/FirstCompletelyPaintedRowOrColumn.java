package brute;

import java.util.AbstractMap;

public class FirstCompletelyPaintedRowOrColumn {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] rowCounter = new int[m];
        int[] colCounter = new int[n];
        AbstractMap.SimpleEntry<Integer, Integer>[] valToPos = (AbstractMap.SimpleEntry<Integer, Integer>[]) new AbstractMap.SimpleEntry[m * n + 1];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                valToPos[mat[i][j]] = new AbstractMap.SimpleEntry<>(i,j);
            }
        }
        for(int i=0; i<arr.length; i++) {
            AbstractMap.SimpleEntry<Integer, Integer> pos = valToPos[arr[i]];
            rowCounter[pos.getKey()] += 1;
            colCounter[pos.getValue()] += 1;
            if(rowCounter[pos.getKey()] == n) {
                return i;
            }
            if(colCounter[pos.getValue()] == m) {
                return i;
            }
        }
        return -1;
    }
}

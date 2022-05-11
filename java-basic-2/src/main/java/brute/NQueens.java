package brute;

import java.util.Collections;
import java.util.List;

/**
 * 51. N-Queens
 * https://leetcode.com/problems/n-queens/
 */
public class NQueens {
    private boolean checkQueue(int x, int y, List<StringBuilder> table, int n) {
        for(var i=0; i<y; i++) {
            if(table.get(i).charAt(x) == 'Q') {
                return false;
            }
        }
        for(int i=x-1, j=y-1; i>=0&&j>=0; i--, j--) {
            if(table.get(j).charAt(i) == 'Q') {
                return false;
            }
        }
        for(int i=x+1, j=y-1; i<n&&j>=0; j--, i++) {
            if(table.get(j).charAt(i) == 'Q') {
                return false;
            }
        }
        return true;
    }

    public List<List<String>> solveNQueens(int n) {
        return Collections.EMPTY_LIST;
    }
}

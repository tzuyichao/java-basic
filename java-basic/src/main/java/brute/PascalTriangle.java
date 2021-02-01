package brute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * = 118
 */
public class PascalTriangle {
    private List<List<Integer>> pascalTriangle(int numRows) {
        if(numRows < 0) {
            return Collections.EMPTY_LIST;
        }
        List<List<Integer>> result = new ArrayList<>();

        for(int i=0; i<numRows; i++) {
            if(i==0) {
                result.add(List.of(1));
            } else if(i==1) {
                result.add(List.of(1, 1));
            } else {
                List<Integer> row = new ArrayList<>();

                row.add(1);
                for(int k=1; k<i; k++) {
                    row.add(result.get(i-1).get(k-1) + result.get(i-1).get(k));
                }
                row.add(1);

                result.add(row);
            }
        }
        return result;
    }

    public List<List<Integer>> generate(int numRows) {
        return pascalTriangle(numRows);
    }
}

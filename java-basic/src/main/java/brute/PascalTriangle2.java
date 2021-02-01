package brute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * = 119
 */
public class PascalTriangle2 {
    private Map<Integer, List<Integer>> memory = new HashMap<>();

    public PascalTriangle2() {
        memory.put(0, List.of(1));
        memory.put(1, List.of(1, 1));
    }

    public List<Integer> getRow(int rowIndex) {
        if(memory.containsKey(rowIndex)) {
            return memory.get(rowIndex);
        } else {
            List<Integer> row = new ArrayList<>();
            List<Integer> previous = getRow(rowIndex-1);
            row.add(1);
            for(int k=1; k<rowIndex; k++) {
                row.add(previous.get(k-1) + previous.get(k));
            }
            row.add(1);

            memory.put(rowIndex, row);
            return row;
        }
    }
}

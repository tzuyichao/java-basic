package collection;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ImmutableCollectionLab {
    public static void main(String[] args) {
        List<String> immutableList = List.of("a", "b", "c");
        Set<Integer> immutableSet = Set.of(1, 2, 3, 4);
        Map<String, Integer> immutableMap = Map.of("one", 1, "two", 2);

        try {
            immutableList.add("d");
        } catch(UnsupportedOperationException e) {
            System.out.println("immutable collection cannot use add() operations");
        }

        try {
            immutableSet.add(6);
        } catch(UnsupportedOperationException e) {
            System.out.println("immutable collection cannot use add() operations");
        }

        try {
            immutableMap.put("three", 3);
        } catch(UnsupportedOperationException e) {
            System.out.println("immutable collection cannot use add() operations");
        }

        Map<String, Integer> immutableMap2 = Map.ofEntries(
                Map.entry("one", 1),
                Map.entry("two", 2)
        );

        try {
            immutableMap2.put("three", 3);
        } catch(UnsupportedOperationException e) {
            System.out.println("immutable collection cannot use add() operations");
        }
    }
}

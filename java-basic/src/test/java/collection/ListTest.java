package collection;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ListTest {
    @Test
    public void simple_add_null() {
        List<Integer> l = new ArrayList<>();
        l.add(null);
        assertEquals(1, l.size());
    }

    @Test
    public void test_equals_of_list_of() {
        assertEquals(List.of(2, 2), List.of(2, 2));
        assertTrue(List.of(2, 2).equals(List.of(2, 2)));
    }

    @Test
    public void test_set() {
        Set<List<Integer>> set = new HashSet<>();
        set.add(List.of(2, 2));
        set.add(Collections.EMPTY_LIST);
        set.add(List.of(2, 2));
        assertEquals(2, set.size());
    }
}

package collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListTest {
    @Test
    public void simple_add_null() {
        List<Integer> l = new ArrayList<>();
        l.add(null);
        assertEquals(1, l.size());
    }
}

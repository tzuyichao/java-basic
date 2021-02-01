package brute;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PascalTriangleTest {
    PascalTriangle pascalTriangle;
    @Before
    public void setup() {
        pascalTriangle = new PascalTriangle();
    }

    @Test
    public void simple1() {
        List<List<Integer>> result = pascalTriangle.generate(0);
        assertEquals(Collections.EMPTY_LIST, result);
    }

    @Test
    public void simple2() {
        List<List<Integer>> result = pascalTriangle.generate(1);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(java.util.Optional.of(1).get(), result.get(0).get(0));
    }

    @Test
    public void simple3() {
        List<List<Integer>> result = pascalTriangle.generate(5);
        assertNotNull(result);
        System.out.println(result);
        assertEquals(5, result.size());
        assertEquals(java.util.Optional.of(1).get(), result.get(0).get(0));
    }
}

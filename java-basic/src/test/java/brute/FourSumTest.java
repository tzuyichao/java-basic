package brute;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class FourSumTest {
    FourSum solver;

    @Before
    public void init() {
        solver = new FourSum();
    }

    @Test
    public void simple1() {
        List<List<Integer>> rs = solver.fourSum(new int[] {1, 0, -1, 0, -2, 2}, 0);
        assertNotNull(rs);
        assertEquals(3, rs.size());
        System.out.println(rs);
    }

    @Test
    public void simple2() {
        List<List<Integer>> rs = solver.fourSum(new int[] {}, 0);
        assertNotNull(rs);
        assertEquals(0, rs.size());
    }

    @Test
    public void simple3() {
        List<List<Integer>> rs = solver.fourSum(new int[] {1, 0, -1, 0}, 0);
        assertNotNull(rs);
        assertEquals(1, rs.size());
        assertTrue(rs.contains(List.of(1, 0, -1, 0)));
    }

    @Test
    public void simple4() {
        List<List<Integer>> rs = solver.fourSum(new int[] {1, 0, -1, 0}, 1);
        assertNotNull(rs);
        assertEquals(0, rs.size());
    }

    @Test
    public void simple5() {
        List<List<Integer>> rs = solver.fourSum(new int[] {-2, -1, -1, 1, 1, 2, 2}, 0);
        assertNotNull(rs);
        System.out.println(rs.stream().distinct().collect(Collectors.toList()));
        assertEquals(2, rs.size());
    }
}

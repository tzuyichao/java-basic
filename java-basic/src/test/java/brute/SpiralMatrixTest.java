package brute;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Ignore
public class SpiralMatrixTest {
    SpiralMatrix solver;

    @Before
    public void init() {
        solver = new SpiralMatrix();
    }

    @Test
    public void simple1() {
        assertEquals(new int[] {1,2,3,6,9,8,7,4,5}, solver.spiralOrder(new int[][] {{1,2,3},{4,5,6},{7,8,9}}));
    }

    @Test
    public void simple2() {
        assertEquals(new int[] {1,2,3,4,8,12,11,10,9,5,6,7}, solver.spiralOrder(new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12}}));
    }

    @Test
    public void simple3() {
        assertEquals(new int[] {1,2,3,4}, solver.spiralOrder(new int[][] {{1,2,3,4}}));
    }
}

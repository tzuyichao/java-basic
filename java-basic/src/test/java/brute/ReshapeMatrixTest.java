package brute;

import org.junit.Before;
import org.junit.Test;

import static brute.AssertHelper.assertEquals;

public class ReshapeMatrixTest {
    ReshapeMatrix solver;

    @Before
    public void init() {
        solver = new ReshapeMatrix();
    }

    @Test
    public void simple1(){
        int[][] s = new int[][] {{1, 2}, {3, 4}};
        int[][] r = solver.matrixReshape(s, 1, 4);
        assertEquals(new int[][] {{1,2,3,4}}, r);
    }

    @Test
    public void simple2(){
        int[][] s = new int[][] {{1, 2}, {3, 4}};
        int[][] r = solver.matrixReshape(s, 1, 2);
        assertEquals(s, r);
    }

    @Test
    public void simple3(){
        int[][] s = new int[][] {{1, 2}, {3, 4}};
        int[][] r = solver.matrixReshape(s, 3, 5);
        assertEquals(s, r);
    }
}

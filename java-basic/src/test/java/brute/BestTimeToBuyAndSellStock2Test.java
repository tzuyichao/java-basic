package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BestTimeToBuyAndSellStock2Test {
    BestTimeToBuyAndSellStock2 solver;

    @Before
    public void init() {
        solver = new BestTimeToBuyAndSellStock2();
    }
    
    @Test
    public void simple1() {
        int[] prices = new int[] {7,1,5,3,6,4};
        assertEquals(7, solver.maxProfit(prices));
    }

    @Test
    public void simple2() {
        int[] prices = new int[] {1,2,3,4,5};
        assertEquals(4, solver.maxProfit(prices));
    }

    @Test
    public void simple3() {
        int[] prices = new int[] {7,6,4,3,1};
        assertEquals(0, solver.maxProfit(prices));
    }

    @Test
    public void simple4() {
        int[] prices = new int[] {7};
        assertEquals(0, solver.maxProfit(prices));
    }

    @Test
    public void simple5() {
        int[] prices = new int[] {7,1,2,1,4,9};
        assertEquals(9, solver.maxProfit(prices));
    }

    @Test
    public void simple6() {
        int[] prices = new int[] {7,1,2,3,4,9};
        assertEquals(8, solver.maxProfit(prices));
    }
}

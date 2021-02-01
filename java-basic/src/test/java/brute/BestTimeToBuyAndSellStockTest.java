package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BestTimeToBuyAndSellStockTest {
    BestTimeToBuyAndSellStock solver;

    @Before
    public void init() {
        solver = new BestTimeToBuyAndSellStock();
    }

    @Test
    public void simple1() {
        int[] prices = new int[] {7,1,5,3,6,4};
        assertEquals(5, solver.maxProfit(prices));
    }

    @Test
    public void simple2() {
        int[] prices = new int[] {7,6,4,3,1};
        assertEquals(0, solver.maxProfit(prices));
    }

    @Test
    public void simple3() {
        int[] prices = new int[] {7,3,5,1,6,4};
        assertEquals(5, solver.maxProfit(prices));
    }
}

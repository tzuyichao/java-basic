package brute;

import org.junit.Test;

public class CombinationSum2Test {
    @Test
    public void testHappy1() {
        CombinationSum2 solver = new CombinationSum2();
        var result = solver.combinationSum2(new int[] {10,1,2,7,6,1,5}, 8);
        System.out.println(result);
    }

    @Test
    public void testHappy2() {
        CombinationSum2 solver = new CombinationSum2();
        var result = solver.combinationSum2(new int[] {2,5,2,1,2}, 5);
        System.out.println(result);
    }

    @Test
    public void testHappy3() {
        CombinationSum2 solver = new CombinationSum2();
        var result = solver.combinationSum2(new int[] {2}, 5);
        System.out.println(result);
    }

    @Test
    public void testHappy4() {
        CombinationSum2 solver = new CombinationSum2();
        var result = solver.combinationSum2(new int[] {5}, 5);
        System.out.println(result);
    }
}

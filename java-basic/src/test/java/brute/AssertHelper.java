package brute;

import org.junit.Assert;

public class AssertHelper {
    public static void assertEquals(int[][] expect, int[][] actual) {
        Assert.assertEquals(expect.length, actual.length);
        for(int i=0; i<expect.length; i++) {
            Assert.assertEquals(expect[i].length, actual[i].length);
            for(int j=0; j<expect[i].length; j++) {
                Assert.assertEquals(expect[i][j], actual[i][j]);
            }
        }
    }
}

package brute;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SummaryRangesTest {
    SummaryRanges solver;

    @Before
    public void init() {
        solver = new SummaryRanges();
    }

    private void verifyContent(List<String> expect, List<String> actual) {
        assertEquals(expect.size(), actual.size());
        for (int i=0; i<expect.size(); i++) {
            assertEquals(expect.get(i), actual.get(i));
        }
    }

    @Test
    public void simple1() {
        int[] nums = new int[] {0,1,2,4,5,7};
        List<String> expect = List.of("0->2", "4->5", "7");
        List<String> result = solver.summaryRanges(nums);
        assertNotNull(result);
        verifyContent(expect, result);
    }

    @Test
    public void simple2() {
        int[] nums = new int[] {0,2,3,4,6,8,9};
        List<String> expect = List.of("0", "2->4", "6", "8->9");
        List<String> result = solver.summaryRanges(nums);
        assertNotNull(result);
        verifyContent(expect, result);
    }

    @Test
    public void simple3() {
        int[] nums = new int[] {};
        List<String> expect = Collections.EMPTY_LIST;
        List<String> result = solver.summaryRanges(nums);
        assertNotNull(result);
        verifyContent(expect, result);
    }

    @Test
    public void simple4() {
        int[] nums = new int[] {-1};
        List<String> expect = List.of("-1");
        List<String> result = solver.summaryRanges(nums);
        assertNotNull(result);
        verifyContent(expect, result);
    }

    @Test
    public void simple5 () {
        int[] nums = new int[] {0};
        List<String> expect = List.of("0");
        List<String> result = solver.summaryRanges(nums);
        assertNotNull(result);
        verifyContent(expect, result);
    }
}

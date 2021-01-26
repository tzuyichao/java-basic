package brute;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MaximumSubArrayTest {
    MaximumSubArray maximumSubArray;

    @Before
    public void init() {
        maximumSubArray = new MaximumSubArray();
    }

    @Test
    public void simple1() {
        int[] arr = new int[] {-100};
        assertEquals(-100, maximumSubArray.maxSubArray(arr));
    }

    @Test
    public void simple2() {
        int[] arr = new int[] {1};
        assertEquals(1, maximumSubArray.maxSubArray(arr));
    }

    @Test
    public void simple3() {
        int[] arr = new int[] {-2,1,-3,4,-1,2,1,-5,4};
        assertEquals(6, maximumSubArray.maxSubArray(arr));
    }

    @Test
    public void submit_error_1() {
        int[] arr = new int[] {-2,1};
        assertEquals(1, maximumSubArray.maxSubArray(arr));
    }

    @Test
    public void submit_time_limit_exceed_1() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("maximum_subarray_large.txt");

        String content = new String(inputStream.readAllBytes());
//        System.out.println(content);
        String[] contentSplit = content.split(",");
        int[] data = List.of(contentSplit)
                .stream()
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();
        Instant start = Instant.now();
        assertEquals(11081, maximumSubArray.maxSubArray(data));
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).getSeconds());
    }
}

package brute;

import java.util.ArrayList;
import java.util.List;

/**
 * = 1281
 * Runtime: 2 ms, faster than 7.58% of Java online submissions for Subtract the Product and Sum of Digits of an Integer.
 * Memory Usage: 35.6 MB, less than 87.26% of Java online submissions for Subtract the Product and Sum of Digits of an Integer.
 */
public class SubtractProductAndSumOfDigitsOfAnInteger {
    public List<Integer> decompose(int n) {
        List<Integer> res = new ArrayList<>();

        while(n / 10 > 0) {
            res.add(n%10);
            n /= 10;
        }
        res.add(n%10);

        return res;
    }

    public int subtractProductAndSum(int n) {
        List<Integer> dec = decompose(n);

        //System.out.println(dec.stream().reduce(1, (a, b) -> a * b));

        return dec.stream().reduce(1, (a, b) -> a * b) - dec.stream().reduce(0, (a, b) -> a + b);
    }
}

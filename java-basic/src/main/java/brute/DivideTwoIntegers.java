package brute;

/**
 * 29. Divide Two Integers
 *
 * Runtime: 3 ms, faster than 34.12% of Java online submissions for Divide Two Integers.
 * Memory Usage: 41.4 MB, less than 13.93% of Java online submissions for Divide Two Integers.
 */
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        int sign = 1;
        if((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            sign = -1;
        }
        long d = Math.abs((long) dividend);
        long q = Math.abs((long) divisor);

        if(q == 0) return Integer.MAX_VALUE;
        if(d == 0 || d < q) return 0;

        long lans = longdivide(d, q);
        int ans = 0;
        if(lans > Integer.MAX_VALUE) {
            ans = (sign == 1)?Integer.MAX_VALUE:Integer.MIN_VALUE;
        } else {
            ans = (int) (sign * lans);
        }
        return ans;
    }

    private long longdivide(long d, long q) {
        if(d < q) return 0;

        long sum = q;
        long multiple = 1;
        while((sum + sum) <= d) {
            sum += sum;
            multiple += multiple;
        }
        return multiple + longdivide(d-sum, q);
    }
}

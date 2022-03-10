package brute;

/**
 * 201. Bitwise AND of Numbers Range
 * https://leetcode.com/problems/bitwise-and-of-numbers-range/
 */
public class BitwiseANDofNumbersRange {
    public int rangeBitwiseAnd(int left, int right) {
        int c = 0;

        while(left < right) {
            left = left >> 1;
            right = right >> 1;
            c++;
        }

        return left << c;
    }
}

package brute;

import java.util.HashMap;
import java.util.Map;

public class FindUniqueBinaryString {
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        Map<Integer, Boolean> hash = new HashMap<>();
        for (String num : nums) {
            int a = 0;
            for (int i = 0; i < n; ++i) {
                a <<= 1;
                a |= num.charAt(i) - '0';
            }
            hash.put(a, true);
        }
        char[] ans = new char[n];
        for (int i = 0; i < n; i++) {
            ans[i] = '0';
        }
        for (int a = 0; a <= n; ++a) {
            if (!hash.containsKey(a)) {
                for (int i = n - 1; i >= 0; --i) {
                    if ((a & 1) == 1) {
                        ans[i] = '1';
                    }
                    a >>= 1;
                }
                break;
            }
        }
        return new String(ans);
    }

    public static void main(String[] args) {
        String[] nums = {"1010", "1001", "1011", "1110"};
        System.out.println(new FindUniqueBinaryString().findDifferentBinaryString(nums));
    }
}

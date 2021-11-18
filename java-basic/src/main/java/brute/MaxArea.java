package brute;

/**
 * 11. Container With Most Water
 *
 * two point version
 * Runtime: 3 ms, faster than 85.45% of Java online submissions for Container With Most Water.
 * Memory Usage: 52.6 MB, less than 73.55% of Java online submissions for Container With Most Water.
 */
public class MaxArea {
    public int maxArea(int[] height) {
        int res = 0;
        int left = 0;
        int right = height.length - 1;

        while(left < right) {
            res = Math.max(res, Math.min(height[right], height[left]) * (right-left));
            if(height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return res;
    }
}

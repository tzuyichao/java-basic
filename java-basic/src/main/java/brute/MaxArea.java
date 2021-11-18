package brute;

/**
 * 11. Container With Most Water
 * Time Limit Exceeded version
 */
public class MaxArea {
    public int maxArea(int[] height) {
        int res = 0;

        for(int i=0; i<height.length-1; i++) {
            for(int j=i+1; j<height.length; j++) {
                int p;
                if(height[i] > height[j]) {
                    p = height[j] * (j-i);
                } else {
                    p = height[i] * (j-i);
                }
                if(p > res) {
                    res = p;
                }
            }
        }

        return res;
    }
}

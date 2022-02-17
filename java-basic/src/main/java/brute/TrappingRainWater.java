package brute;

/**
 * 42. Trapping Rain Water
 */
public class TrappingRainWater {
    public int volume(int[] height, int start, int end) {
        int h = height[start] > height[end]?height[end]:height[start];

        int result = 0;

        for(int i=start+1; i<end; i++) {
            result += (h-height[i]);
        }

        return result;
    }

    public int trap(int[] height) {
        int r = 0;
        int l = height.length;

        int c = height[0];
        int p1 = -1;
        for(int i=1; i<l; i++) {
            if(height[i] < c) {
                if(p1 == -1) {
                    p1 = i-1;
                } else {
                    System.out.println("p1:" + p1 + ", p2:" + (i-1) );
                    r += volume(height, p1, i-1);
                    p1 = i-1;
                }
            }
            c = height[i];
        }

        return r;
    }
}

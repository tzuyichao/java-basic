package brute;

/**
 * https://www.bookstack.cn/read/algorithm-exercise-tw/binary_search-search_for_a_range.md
 */
public class SearchForARange {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};

        if(nums == null || nums.length == 0) {
            return res;
        }

        int left = 0;
        int right = nums.length-1;
        while(left+1 < right) {
            int mid = left + (right - left)/2;
            if(nums[mid] == target) {
                right = mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if(nums[left] == target) {
            res[0] = left;
        } else if(nums[right] == target) {
            res[0] = right;
        } else {
            return res;
        }

        // find right
        left = 0;
        right = nums.length-1;
        while(left + 1 < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                left = mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if(nums[right] == target) {
            res[1] = right;
        } else if(nums[left] == target) {
            res[1] = left;
        } else {
            return res;
        }

        return res;
    }

    public static void main(String[] args) {
        var solver = new SearchForARange();
        int[] res = solver.searchRange(new int[] {5,7,7,8,8,10}, 8);
        System.out.println(String.format("%d, %d", res[0], res[1]));
    }
}

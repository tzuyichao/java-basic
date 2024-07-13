package brute;

public class FindNumbersWithEvenNumberOfDigits {
    public int digits(int n) {
        int res = 1;
        int quotient = n / 10;
        while(quotient != 0) {
            quotient /= 10;
            res += 1;
        }
        return res;
    }

    public int findNumbers(int[] nums) {
        int res = 0;
        for(int num : nums) {
            if(digits(num) % 2 == 0) {
                res += 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindNumbersWithEvenNumberOfDigits solution = new FindNumbersWithEvenNumberOfDigits();

        System.out.println(solution.digits(1));
        System.out.println(solution.digits(12));
        System.out.println(solution.digits(111));
        System.out.println(solution.digits(1111));

        int[] nums1 = new int[] {12,345,2,6,7896};
        assert solution.findNumbers(nums1) == 2;
        int[] nums2 = new int[] {555,901,482,1771};
        assert solution.findNumbers(nums2) == 1;

        int[] nums3 = new int[] {555,901,482,17711};
        assert solution.findNumbers(nums2) == 0;
    }
}

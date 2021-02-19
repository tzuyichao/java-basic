package brute;

/**
 * = 1287
 */
public class ElementAppearingMoreThan25PercentInSortedArray {
    public int findSpecialInteger(int[] arr) {
        if(arr.length == 1) {
            return arr[0];
        }
        int t = arr.length / 4;
        int c = 1;
        int pre = arr[0];
        for(int i=1; i<arr.length; i++) {
            if(arr[i] == pre) {
                c+=1;
                if(c > t) {
                    return pre;
                }
            } else {
                pre = arr[i];
                c = 1;
            }
        }
        return 0;
    }
}

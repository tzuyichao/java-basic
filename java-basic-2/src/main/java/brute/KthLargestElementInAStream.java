package brute;

/**
 * 703. Kth Largest Element in a Stream
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
 * 
 */
class KthLargest {
    private int k = 0;
    private int[] store;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.store = new int[k];
        for(int i=0; i<k; i++) {
            store[i] = Integer.MIN_VALUE;
        }
        if(nums.length > 0) {
            for(int i=0; i<nums.length; i++) {
                add(nums[i]);
            }
            //inspect();
        }
    }

    public void inspect() {
        for(int i=0; i<store.length; i++) {
            System.out.print(store[i] + " ");
        }
        System.out.println();
    }

    /**
     * add val then get kth largest element
     */
    public int add(int val) {
        //inspect();
        boolean inserted = false;
        int idx = 0;
        for(int i=0; i<store.length; i++) {
            if(store[i] < val) {
                idx = i;
                inserted = true;
                break;
            }
        }
        if(inserted) {
            if(idx == k-1) {
                store[idx] = val;
            } else {
                for(int i=k-2; i>=idx; i--) {
                    store[i+1] = store[i];
                }
                //System.out.println("add " + val + " to " + idx);
                store[idx] = val;
            }
        }

        return store[k-1];
    }
}

public class KthLargestElementInAStream {
    public static void main(String[] args) {
        var k = new KthLargest(3, new int[] {4, 5, 8, 2});
        System.out.println(k.add(3) == 4);
        System.out.println(k.add(5) == 5);
        System.out.println(k.add(10) == 5);
        System.out.println(k.add(9) == 8);
        System.out.println(k.add(4) == 8);
        System.out.println("Done.");
    }
}

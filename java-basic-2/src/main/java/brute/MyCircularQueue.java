package brute;

/**
 * 622. Design Circular Queue
 * https://leetcode.com/problems/design-circular-queue/
 *
 * Runtime: 5 ms, faster than 77.59% of Java online submissions for Design Circular Queue.
 * Memory Usage: 48.4 MB, less than 33.00% of Java online submissions for Design Circular Queue.
 */
public class MyCircularQueue {
    private int[] data;
    private int slow;
    private int fast;
    private int size;
    private final int k;

    public MyCircularQueue(int k) {
        this.k = k;
        slow = 0;
        fast = -1;
        size = 0;
        data = new int[k];
    }

    public boolean enQueue(int value) {
        if(isFull()) {
            return false;
        }
        fast = (fast+1)%k;
        data[fast] = value;
        size++;
        return true;
    }

    public boolean deQueue() {
        if(size == 0) {
            return false;
        }
        slow = (slow+1)%k;
        size--;
        return true;
    }

    public int Front() {
        if(isEmpty()) {
            return -1;
        }
        return data[slow];
    }

    public int Rear() {
        if(isEmpty()) {
            return -1;
        }
        return data[fast];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == k;
    }
}

package brute;

/**
 * 641. Design Circular Deque
 * https://leetcode.com/problems/design-circular-deque/
 *
 * Runtime: 10 ms, faster than 12.40% of Java online submissions for Design Circular Deque.
 * Memory Usage: 48.5 MB, less than 49.32% of Java online submissions for Design Circular Deque.
 */
public class MyCircularDeque {
    private int[] data;
    private final int size;
    private int front;
    private int last;

    public MyCircularDeque(int k) {
        data = new int[2000];
        size = k;
        front = -1;
        last = -1;
    }

    public boolean insertFront(int value) {
        if(isFull()) {
            return false;
        }
        if(front == -1) {
            front = 0;
            last = 0;
        } else if(front == 0) {
            front = size-1;
        } else {
            front -= 1;
        }
        data[front] = value;
        return true;
    }

    public boolean insertLast(int value) {
        if(isFull()) {
            return false;
        }
        if(front == -1) {
            front = 0;
            last = 0;
        } else if (last == size-1) {
            last = 0;
        } else {
            last += 1;
        }
        data[last] = value;
        return true;
    }

    public boolean deleteFront() {
        if(isEmpty()) {
            return false;
        }
        if(front == last) {
            front = -1;
            last = -1;
        } else {
            if(front == size-1) {
                front = 0;
            } else {
                front += 1;
            }
        }
        return true;
    }

    public boolean deleteLast() {
        if(isEmpty()) {
            return false;
        }
        if(front == last) {
            front = -1;
            last = -1;
        } else if(last == 0) {
            last = size-1;
        } else {
            last -= 1;
        }
        return true;
    }

    public int getFront() {
        if(isEmpty()) {
            return -1;
        }
        return data[front];
    }

    public int getRear() {
        if(isEmpty()) {
            return -1;
        }
        return data[last];
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return ((front == 0 && last == size-1) || front == last+1);
    }
}

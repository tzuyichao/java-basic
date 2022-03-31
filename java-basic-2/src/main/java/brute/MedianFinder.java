package brute;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 295. Find Median from Data Stream
 * https://leetcode.com/problems/find-median-from-data-stream/
 *
 * Runtime: 274 ms, faster than 15.44% of Java online submissions for Find Median from Data Stream.
 * Memory Usage: 125.8 MB, less than 26.21% of Java online submissions for Find Median from Data Stream.
 */
public class MedianFinder {
    private Queue<Integer> left;
    private Queue<Integer> right;
    boolean even;

    public MedianFinder() {
        left = new PriorityQueue<>();
        right = new PriorityQueue<>(Collections.reverseOrder());
        even = true;
    }

    public void addNum(int num) {
        if(even) {
            left.add(num);
            right.add(left.poll());
        } else {
            right.add(num);
            left.add(right.poll());
        }
        even = !even;
    }

    public double findMedian() {
        return even?(right.peek() + left.peek())/2.0: right.peek();
    }
}

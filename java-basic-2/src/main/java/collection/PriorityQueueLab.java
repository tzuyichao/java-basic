package collection;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueLab {
    public static void test1() {
        Queue<Integer> queue1 = new PriorityQueue<>();
        System.out.println(queue1);
        queue1.add(5);
        System.out.println(queue1);
        queue1.add(1);
        System.out.println(queue1);
        queue1.add(7);
        System.out.println(queue1);
        queue1.poll();
        System.out.println(queue1);
        Queue<Integer> queue2 = new PriorityQueue<>(Comparator.reverseOrder());
        System.out.println(queue2);
        queue2.add(5);
        System.out.println(queue2);
        queue2.add(1);
        System.out.println(queue2);
        queue2.add(7);
        System.out.println(queue2);
        queue2.poll();
        System.out.println(queue2);
    }

    public static void test2() {
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        q.offer(3);
        q.offer(5);
        q.offer(9);
        System.out.println(q);
    }
    public static void main(String[] args) {
        test2();
    }
}

package brute;

import java.util.ArrayDeque;
import java.util.Deque;

public class NumberOfRecentCalls {
    static class RecentCounter {
        private Deque<Integer> queue = new ArrayDeque<>();

        public RecentCounter() {

        }

        public int ping(int t) {
            while(!queue.isEmpty() && queue.peek() < t - 3000) {
                queue.poll();
            }
            queue.add(t);
            return queue.size();
        }
    }

    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        System.out.println(recentCounter.ping(1)); // 1
        System.out.println(recentCounter.ping(100)); // 2
        System.out.println(recentCounter.ping(3001)); // 3
        System.out.println(recentCounter.ping(3002)); // 3
    }
}

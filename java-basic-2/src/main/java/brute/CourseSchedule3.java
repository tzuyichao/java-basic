package brute;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 630. Course Schedule III
 * https://leetcode.com/problems/course-schedule-iii/
 *
 * https://www.cnblogs.com/grandyang/p/7126289.html
 */
public class CourseSchedule3 {
    public int scheduleCourse(int[][] courses) {
        int cur = 0;
        PriorityQueue<Integer> q = new PriorityQueue(Comparator.reverseOrder());
        Arrays.sort(courses, (a, b) -> {
            if(a[1] == b[1]) {
                return 0;
            } else if(a[1] > b[1]) {
                return 1;
            } else {
                return -1;
            }
        });
        for(int[] course : courses) {
            //System.out.printf("%d %d %d\n", course[0], course[1], cur);
            //System.out.println(q);
            cur += course[0];
            q.offer(course[0]);
            //System.out.println(q);
            if(cur > course[1]) {
                int co = q.poll();
                cur -= co;
            }
        }
        return q.size();
    }
}

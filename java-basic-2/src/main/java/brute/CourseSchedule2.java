package brute;

import java.util.*;

/**
 * 210. Course Schedule II
 * https://leetcode.com/problems/course-schedule-ii/
 *
 * Runtime: 16 ms, faster than 27.55% of Java online submissions for Course Schedule II.
 * Memory Usage: 49.7 MB, less than 38.80% of Java online submissions for Course Schedule II.
 */
public class CourseSchedule2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }
        int[] in = new int[numCourses];
        for(int i=0; i<prerequisites.length; i++) {
            List<Integer> list = graph.get(prerequisites[i][1]);
            list.add(prerequisites[i][0]);
            in[prerequisites[i][0]]+=1;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i=0; i<numCourses; i++) {
            if(in[i] == 0) {
                queue.add(i);
            }
        }
        while(!queue.isEmpty()) {
            int c = queue.removeFirst();
            res.add(c);
            for(Integer course: graph.get(c)) {
                in[course]-=1;
                if(in[course] == 0) {
                    queue.add(course);
                }
            }
        }
        if(res.size() != numCourses) {
            res.clear();
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}

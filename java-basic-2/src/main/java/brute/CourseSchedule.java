package brute;

import java.util.LinkedList;

/**
 * 207. Course Schedule
 * https://leetcode.com/problems/course-schedule/
 *
 * Runtime: 25 ms, faster than 15.78% of Java online submissions for Course Schedule.
 * Memory Usage: 46.4 MB, less than 67.90% of Java online submissions for Course Schedule.
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int n = prerequisites.length;
        if(0 == numCourses || 0 == n) {
            return true;
        }
        int[] preCounter = new int[numCourses];
        for(int i=0; i<n; i++) {
            preCounter[prerequisites[i][0]]+=1;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i=0; i<numCourses; i++) {
            if(preCounter[i] == 0) {
                queue.add(i);
            }
        }
        int sizeOfNoPre = queue.size();

        while(!queue.isEmpty()) {
            int top = queue.remove();
            for(int i=0; i<n; i++) {
                if(prerequisites[i][1] == top) {
                    preCounter[prerequisites[i][0]]-=1;
                    if(preCounter[prerequisites[i][0]] == 0) {
                        sizeOfNoPre+=1;
                        queue.add(prerequisites[i][0]);
                    }
                }
            }
        }

        return sizeOfNoPre == numCourses;
    }
}

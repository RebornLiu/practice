import java.util.LinkedList;
import java.util.Queue;

public class CourseCan {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 1) {
            return true;
        }

        //计算入度
        int[] degrees = new int[numCourses];
        for (int[] dep : prerequisites) {
            degrees[dep[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i ++) {
            if (degrees[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            numCourses --;
            for (int[] p : prerequisites) {
                if (p[0] == node) {
                    degrees[p[1]]--;
                    if (degrees[p[1]] == 0) {
                        queue.add(p[1]);
                    }
                }
            }
        }

        return numCourses == 0;
    }


}

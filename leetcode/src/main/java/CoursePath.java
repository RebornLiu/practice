public class CoursePath {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] degree = new int[numCourses];
        for (int[] p : prerequisites) {
            degree[p[0]] ++;
        }

        int[] path = new int[numCourses];
        int start = 0;
        int end = 0;

        for (int i = 0; i < degree.length; i ++) {
            if (degree[i] == 0) {
                path[end] = i;
                end ++;
                numCourses --;
            }
        }

        while (start < end) {
            int cur = path[start];
            start ++;
            for (int[] p : prerequisites) {
                if (p[1] == cur) {
                    degree[p[0]] --;
                    if (degree[p[0]] == 0) {
                        path[end] = p[0];
                        end ++;
                        numCourses --;
                    }
                }
            }
        }

        if (numCourses != 0) {
            return new int[0];
        }

        return path;

    }
}

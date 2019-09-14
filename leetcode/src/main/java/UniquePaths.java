public class UniquePaths {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }

        if (m == 1 || n == 1) {
            return 1;
        }

        return uniquePaths(m - 1, n) + uniquePaths(m, n -1);
    }

    public int uniquePaths2(int m, int n) {
        int[][] res = new int[101][101];
        for (int i = 1 ; i < 101; i ++) {
            res[i][1] = 1;
            res[1][i] = 1;
        }

        for (int i = 2; i <= m; i ++) {
            for (int j = 2; j <= n; j ++) {
                res[i][j] = res[i - 1][j] + res[i][j - 1];
            }
        }

        return res[m][n];
    }

    public static void main(String[] args) {
        System.out.println(new UniquePaths().uniquePaths(45,9));
    }
}

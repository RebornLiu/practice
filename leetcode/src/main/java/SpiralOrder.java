import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return Collections.emptyList();
        }

        int cols = matrix[0].length;
        if (cols == 0) {
            return Collections.emptyList();
        }

        List<Integer> arrays = new ArrayList<>();
        int loop = (Math.min(rows, cols) - 1)/ 2 + 1;

        for (int n = 0; n < loop; n ++) {
            System.out.println(n);
            //横向
            for (int col = n; col < cols - n; col ++) {
                arrays.add(matrix[n][col]);
            }

            //纵向
            for (int row = n + 1; row < rows - n; row ++) {
                arrays.add(matrix[row][cols - n - 1]);
            }

            if (rows -n -1 != n) {
                //横向
                for (int col = cols - n - 2; col >= n; col--) {
                    arrays.add(matrix[rows - n - 1][col]);
                }
            }

            if (n != cols - n - 1) {
                //纵向
                for (int row = rows - n - 2; row >= n + 1; row--) {
                    arrays.add(matrix[row][n]);
                }
            }
        }

        return arrays;
    }

    public static void main(String[] args) {
        int[] row1 = new int[]{1};
        int[] row2 = new int[]{4};
        int[] row3 = new int[]{7};
        int[] row4 = new int[]{10};

        int[][] matrix = new int[4][];
        matrix[0] = row1;
        matrix[1] = row2;
        matrix[2] = row3;
        matrix[3] = row4;

        System.out.println(new SpiralOrder().spiralOrder(matrix));

    }
}

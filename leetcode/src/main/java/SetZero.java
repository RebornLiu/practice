public class SetZero {

    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return;
        }
        int cols = matrix[0].length;
        int[] rowMask = new int[matrix.length];
        int[] colMask = new int[matrix[0].length];

        for (int i = 0; i < rows; i ++) {
            for (int j = 0; j < cols; j ++) {
                if (matrix[i][j] == 0) {
                    rowMask[i] = 1;
                    colMask[j] = 1;
                }
            }
        }

        for (int i = 0; i < rows; i ++) {
            for (int j = 0; j < cols; j ++) {
                if (rowMask[i] == 1 || colMask[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}

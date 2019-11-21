public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows < 1) {
            return false;
        }
        int cols = matrix[0].length;
        if (cols < 1) {
            return false;
        }
        return helper(matrix, 0, rows - 1, 0, cols - 1, target);
    }


    public boolean searchMatrix2(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows < 1) {
            return false;
        }
        int cols = matrix[0].length;
        if (cols < 1) {
            return false;
        }

        int rowStart = rows - 1;
        int colStart = 0;
        while (rowStart >= 0 && colStart < cols) {
            if (matrix[rowStart][colStart] == target) {
                return true;
            }
            else if (matrix[rowStart][colStart] > target) {
                rowStart --;
            }
            else {
                colStart ++;
            }
        }
        return false;
    }

    private boolean helper(int[][] matrix, int startRow, int endRow, int startCol, int endCol, int target) {

        if (startRow > endRow) {
            return false;
        }

        if (startCol > endCol) {
            return false;
        }

        int midRow = (startRow + endRow) / 2;
        int midCol = (startCol + endCol) / 2;

        for (int i = startCol; i <= endCol; i ++) {
            if (matrix[midRow][i] == target) {
                return true;
            }
        }

        for (int i = startRow; i <= endRow; i ++) {
            if (matrix[i][midCol] == target) {
                return true;
            }
        }

        if (target > matrix[midRow][midCol]) {
            return
                    helper(matrix, startRow, midRow - 1, midCol + 1, endCol, target) ||
                            helper(matrix, midRow + 1, endRow, startCol, midCol - 1, target) ||
                            helper(matrix, midRow + 1, endRow, midCol + 1, endCol, target);
        }
        else {
            return helper(matrix, startRow, midRow - 1, startCol, midCol - 1, target) ||
                    helper(matrix, startRow, midRow - 1, midCol + 1, endCol, target) ||
                    helper(matrix, midRow + 1, endRow, startCol, midCol - 1, target);
        }
    }

    public static void main(String[] args) {
        int[] line1 = new int[]{1,   4,  7, 11, 15};
        int[] line2 = new int[]{2,   5,  8, 12, 19};
        int[] line3 = new int[]{3,   6,  9, 16, 22};
        int[] line4 = new int[]{10, 13, 14, 17, 24};
        int[] line5 = new int[]{18, 21, 23, 26, 30};

        int[][] matrix = new int[][]{line1, line2, line3, line4, line5};
        System.out.println(new SearchMatrix().searchMatrix2(matrix, 90));
    }
}

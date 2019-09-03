public class Sudoku {

    public boolean isValidSudoku(char[][] board) {

        int[] array = new int[9];

        boolean row = checkRow(board, array);
        boolean col = checkCol(board, array);
        boolean rect = checkRect(board, array);

        return row && col && rect;

    }


    public boolean isValidSudoku2(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] cols = new int[9][9];
        int[][] rects = new int[9][9];

        for (int row = 0; row < 9; row ++) {
            for (int col = 0; col < 9; col ++) {
                if (board[row][col] == '.') {
                    continue;
                }

                int index = board[row][col] - '1';
                //每行
                int cur = rows[row][index];
                if (cur == 0) {
                    rows[row][index] = board[row][col];
                }
                else {
                    return false;
                }

                //每列
                cur = cols[col][index];
                if (cur == 0) {
                    cols[col][index] = board[row][col];
                }
                else {
                    return false;
                }

                //每个方块
                cur = rects[row / 3  * 3 + col / 3][index];
                if (cur == 0) {
                    rects[row / 3  * 3 + col / 3][index] = board[row][col];
                }
                else {
                    return false;
                }
            }
        }

        return true;
    }



    private boolean checkRow(char[][] board, int[] array) {
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int cur = array[(board[i][j] - '1')];
                if (cur == 0) {
                    array[(board[i][j] - '1')] = board[i][j];
                }
                else {
                    return false;
                }
            }
            resetArrayZero(array);
        }

        return true;
    }

    private boolean checkCol(char[][] board, int[] array) {
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++) {
                if (board[j][i] == '.') {
                    continue;
                }
                int cur = array[(board[j][i] - '1')];
                if (cur == 0) {
                    array[(board[j][i] - '1')] = board[j][i];
                }
                else {
                    return false;
                }
            }
            resetArrayZero(array);
        }

        return true;
    }


    private boolean checkRect(char[][] board, int[] array) {
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j ++) {
                for (int k = i * 3; k < i * 3 + 3; k ++) {
                    for (int g = j * 3; g < j * 3 + 3; g ++) {
                        if (board[k][g] == '.') {
                            continue;
                        }
                        int cur = array[board[k][g] - '1'];
                        if (cur == 0) {
                            array[board[k][g] - '1'] = board[k][g];
                        }
                        else {
                            return false;
                        }
                    }
                }
                resetArrayZero(array);
            }
        }

        return true;
    }

    private void resetArrayZero(int[] array) {
        for (int i = 0; i < array.length; i ++) {
            array[i] = 0;
        }
    }
}


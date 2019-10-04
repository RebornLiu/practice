public class BaoWei {
    public void solve(char[][] board) {
       int rows = board.length;
       int cols = board[0].length;

       UnionCollector unionCollector = new UnionCollector(rows * cols + 1);
       for (int i = 0; i < rows; i ++) {
           for (int j = 0; j < cols; j ++) {
               if (board[i][j] != 'O') {
                   continue;
               }

               int cur = i * cols + j;
               if(i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                   unionCollector.union(rows * cols, cur);
                   continue;
               }
               if (board[i - 1][j] == 'O') {
                   unionCollector.union(cur , cur - cols);
               }
               if (board[i + 1][j] == 'O') {
                   unionCollector.union(cur, cur + cols);
               }
               if (board[i][j - 1] == 'O') {
                   unionCollector.union(cur, cur - 1);
               }
               if (board[i][j + 1] == 'O') {
                   unionCollector.union(cur, cur + 1);
               }
           }
       }

       for (int i = 0; i < rows; i ++) {
           for (int j = 0; j < cols; j ++) {
               if (board[i][j] != 'O') {
                    continue;
               }

               if (!unionCollector.isConnect(i * cols + j, rows * cols + 1)) {
                   board[i][j] = 'X';
               }
           }
       }
    }
}

class UnionCollector {
    private int[] array;

    public UnionCollector(int total) {
        array = new int[total];
        for (int i = 0; i < total; i ++) {
            array[i] = i;
        }
    }

    public int find(int i) {
        while(array[i] != i) {
            i = array[array[i]];
        }
        return i;
    }

    public void union(int i, int j) {
        int root1 = find(i);
        int root2 = find(j);
        if (root1 != root2) {
            array[root2] = root1;
        }
    }

    public boolean isConnect(int i, int j) {
        int root1 = find(i);
        int root2 = find(j);
        return root1 == root2;
    }
}
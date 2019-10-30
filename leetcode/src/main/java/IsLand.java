import java.util.LinkedList;
import java.util.Queue;

public class IsLand {

    public int numIslands(char[][] grid) {
        int len = grid.length;
        int wid = grid[0].length;

        int count = 0;
        char[][] helper = new char[len][wid];
        for (int i = 0; i < len; i ++) {
            for (int j = 0; j < wid; j ++) {
                if (grid[i][j] == '1' && helper[i][j] != '1') {
                    count ++;
                    walkIslan(grid, i, j, helper);
                }
            }
        }

        return count;
    }


    private void walkIslan(char[][] grid, int x, int y, char[][] helper) {
        int len = grid.length;
        int wid = grid[0].length;

        if (x + 1 < len) {
            if (helper[x + 1][y] != '1' && grid[x + 1][y] == '1') {
                helper[x + 1][y] = '1';
                walkIslan(grid, x + 1, y, helper);
            }
        }

        if ((y + 1 < wid)) {
            if (helper[x][y + 1] != '1' && grid[x][y + 1] =='1') {
                helper[x][y + 1] = '1';
                walkIslan(grid, x, y + 1, helper);
            }
        }

        if (x - 1 >= 0) {
            if (helper[x - 1][y] != '1' && grid[x - 1][y] == '1') {
                helper[x - 1][y] = '1';
                walkIslan(grid, x - 1, y, helper);
            }
        }

        if (y - 1 >= 0) {
            if (helper[x][y - 1] != '1' && grid[x][y - 1] == '1') {
                helper[x][y - 1] = '1';
                walkIslan(grid, x, y - 1, helper);
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = new char[3][3];
        grid[0] = new char[]{'1', '1', '1'};
        grid[1] = new char[]{'0', '1', '1'};
        grid[2] = new char[]{'1', '1', '1'};

        IsLand isLand = new IsLand();
        int r = isLand.numIslands(grid);
        System.out.println(r);
    }
}

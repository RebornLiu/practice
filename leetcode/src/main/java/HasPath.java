import com.alibaba.fastjson.JSON;

import java.util.*;

public class HasPath {

    public boolean exist(char[][] board, String word) {

        LinkedList<Point> points = this.findStart(board, word.charAt(0));
        if (points.size() == 0) {
            return false;
        }
        List<LinkedList<Point>> path = new ArrayList<>();
        for (Point p : points) {
            LinkedList<Point> pp = new LinkedList<>();
            pp.addLast(p);
            path.add(pp);
        }
        for (int i = 1; i < word.length(); i ++) {
            List<LinkedList<Point>> nnnn = new ArrayList<>();
            for (LinkedList<Point> ps : path) {
                Point last = ps.getLast();
                List<Point> nextP = findNext(board, last, word.charAt(i));
                if (nextP.size() != 0) {
                    for (Point n : nextP) {
                        LinkedList<Point> nP = new LinkedList<>(ps);
                        nP.addLast(n);
                        nnnn.add(nP);
                    }
                }
            }
            if (nnnn.isEmpty()) {
                return false;
            }
            path = nnnn;
        }

        System.out.println(JSON.toJSONString(path));
        return true;

    }


    private List<Point> findNext(char[][] board, Point last, char ch) {
        int len = board.length;
        int wid = board[0].length;
        List<Point> nextes = new ArrayList<>();
        if (last.y > 0) {
            if (board[last.x][last.y - 1] == ch) {
                nextes.add(new Point(last.x, last.y - 1));
            }
        }

        if (last.y < wid - 1) {
            if (board[last.x][last.y + 1] == ch) {
                nextes.add(new Point(last.x, last.y + 1));
            }
        }

        if (last.x > 0) {
            if (board[last.x - 1][last.y] == ch) {
                nextes.add(new Point(last.x - 1, last.y));
            }
        }

        if (last.x < len - 1) {
            if (board[last.x + 1][last.y] == ch) {
                nextes.add(new Point(last.x + 1, last.y));
            }
        }

        return nextes;
    }

    private LinkedList<Point> findStart(char[][] board, char start) {
        int len = board.length;
        int width = board[0].length;
        LinkedList<Point> points = new LinkedList<>();
        for (int i = 0; i < len; i ++) {
            for (int j = 0; j < width; j ++) {
                if (board[i][j] == start) {
                    points.add(new Point(i, j));
                }
            }
        }

        return points;
    }


    public static void main(String[] args) {
        char[][] board = new char[3][];

        board[0] = new char[]{'A','B','C','E'};
        board[1] = new char[]{'S','F','C','S'};
        board[2] = new char[]{'A','D','E','E'};

        new HasPath().exist(board, "ABCB");
    }


}

class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

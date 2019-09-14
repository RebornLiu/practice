import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeArray {

    public int[][] merge(int[][] intervals) {

        if (intervals == null || intervals.length == 0) {
            return new int[][]{};
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] f, int[] s) {
                return f[0] - s[0];
            }
        });

        int i = 0;
        int len = intervals.length;
        List<int[]> res = new ArrayList<>();

        int start = intervals[0][0];
        int end = intervals[0][1];
        while (i < len) {
            if (i < len - 1 && end >= intervals[i + 1][0]) {
                end = Math.max(intervals[i + 1][1], end);
            }
            else if (i == len - 1){
                res.add(new int[]{start, end});
                break;
            }
            else {
                res.add(new int[]{start, end});
                start = intervals[i + 1][0];
                end = intervals[i + 1][1];
            }

            i ++;
        }

        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        int[][] r = new int[4][];
        int[] row1 = new int[]{1,3};
        int[] row2 = new int[]{2,6};
        int[] row3 = new int[]{8,10};
        int[] row4 = new int[]{15,18};

        r[0] = row1;
        r[1] = row2;
        r[2] = row3;
        r[3] = row4;

        System.out.println(JSON.toJSONString(new MergeArray().merge(r)));
    }
}

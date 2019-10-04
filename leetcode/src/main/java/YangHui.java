import java.util.ArrayList;
import java.util.List;

public class YangHui {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        if (numRows <= 0) {
            return list;
        }

        List<Integer> pre = new ArrayList<>(1);
        pre.add(1);
        list.add(pre);

        for (int i = 1; i < numRows; i ++) {
            List<Integer> last = new ArrayList<>(pre.size() + 1);
            int base = 0;
            for (int j = 0; j < pre.size(); j ++) {
                last.add(base + pre.get(j));
                base = pre.get(j);
            }
            last.add(pre.get(pre.size() - 1));
            list.add(last);
            pre = last;
        }

        return list;
    }
}
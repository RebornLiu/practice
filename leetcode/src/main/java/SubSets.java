import java.util.ArrayList;
import java.util.List;

public class SubSets {
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null) {
            return null;
        }
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> newList = new ArrayList<>();
            for (List<Integer> set : result) {
                List<Integer> l = new ArrayList<>(set);
                l.add(nums[i]);
                newList.add(l);
            }
            result.addAll(newList);
            List<Integer> list = new ArrayList<>(1);
            list.add(nums[i]);
            result.add(list);
        }

        return result;
    }
}

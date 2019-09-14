import com.alibaba.fastjson.JSON;

import java.util.*;

public class Permute {

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            List<Integer> empty = new ArrayList<>();
            List<List<Integer>> emptys = new ArrayList<>();
            emptys.add(empty);
            return emptys;
        }

        return partition(nums, nums.length);
    }

    private List<List<Integer>> partition(int[] nums, int n) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> all = new ArrayList<>();
        if (n == 1) {
            list.add(nums[0]);
            all.add(list);
            return all;
        }

        List<List<Integer>> sub = partition(nums, n - 1);
        Integer last = nums[n - 1];

        List<List<Integer>> rs = new ArrayList<>();
        for (List<Integer> str : sub) {
            for (int i = 0; i <= str.size(); i ++) {
                List<Integer> newList = new ArrayList<>(str.size() + 1);
                newList.addAll(str);
                newList.add(i, last);
                rs.add(newList);
            }
        }

        return rs;
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();

        backtrace(nums, 0, nums.length, visited, stack, res);
        return res;
    }


    private void backtrace(int[] nums, int dept, int len, Set<Integer> visited, Stack<Integer> stack, List<List<Integer>> res) {
        if (dept == len) {
            res.add(new ArrayList<>(stack));
            return;
        }

        for (int i = 0; i < len; i ++) {
            if (!visited.contains(i)) {
                visited.add(i);
                stack.push(nums[i]);
                backtrace(nums, dept + 1, len, visited, stack, res);
                stack.pop();
                visited.remove(i);
            }
        }
    }

    public List<List<Integer>> permute3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        LinkedHashSet<Integer> set = new LinkedHashSet<>();

        backTrace(nums, set, res);
        return res;
    }

    private void backTrace(int[] nums, LinkedHashSet<Integer> set, List<List<Integer>> res) {
        if (set.size() == nums.length) {
            res.add(new ArrayList<>(set));
            return;
        }

        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
                backTrace(nums, set, res);
                set.remove(num);
            }
        }
    }



    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(JSON.toJSONString(new Permute().permute3(nums)));
    }
}

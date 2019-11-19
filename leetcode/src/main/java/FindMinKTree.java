import java.util.ArrayList;
import java.util.List;

/**
 * @author by liuweiliang1
 * @date 2019/11/10 17:28
 * @description
 */
public class FindMinKTree {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> visited = new ArrayList<>();
        return helper(root, k, visited);
    }

    private int helper(TreeNode root, int k, List<Integer> visited) {
        if (visited.size() >= k) {
            return visited.get(k - 1);
        }

        if (root.left != null) {
            helper(root.left, k, visited);
        }
        if (visited.size() >= k) {
            return visited.get(k - 1);
        }
        visited.add(root.val);
        if (visited.size() >= k) {
            return visited.get(k - 1);
        }
        if (root.right != null) {
            return helper(root.right, k, visited);
        }

        return Integer.MIN_VALUE;
    }



}

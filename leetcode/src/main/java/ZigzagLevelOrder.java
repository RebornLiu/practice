import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;

public class ZigzagLevelOrder {
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> helper = new Stack<>();
        stack.push(root);
        boolean right = true;
        List<Integer> sub = new ArrayList<>(1);
        sub.add(root.val);
        result.add(sub);
        while(!stack.isEmpty()) {
            sub = new ArrayList<>();
            int sSize = stack.size();
            for(int i = 0; i < sSize; i ++) {
                TreeNode cur = stack.pop();
                if(right) {
                    if(cur.right != null) {
                        helper.add(cur.right);
                        sub.add(cur.right.val);
                    }
                    if(cur.left != null) {
                        helper.add(cur.left);
                        sub.add(cur.left.val);
                    }
                }
                else {
                    if(cur.left != null) {
                        helper.add(cur.left);
                        sub.add(cur.left.val);
                    }
                    if(cur.right != null) {
                        helper.add(cur.right);
                        sub.add(cur.right.val);
                    }
                }
            }
            if(sub.size() == 0) {
                return result;
            }
            result.add(sub);
            right = !right;
            Stack<TreeNode> tmp = stack;
            stack = helper;
            helper = tmp;
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node3.right = node5;

        ZigzagLevelOrder order = new ZigzagLevelOrder();
        List<List<Integer>> result = order.zigzagLevelOrder(root);  

    }
}
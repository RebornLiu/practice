import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * tree 中序遍历
 */
public class TreeMiddleFlow {
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }

        List<Integer> list = new ArrayList<>();
        walkerNode(root, list);

        return list;
    }

    public void walkerNode(TreeNode node, List<Integer> records) {
        if(node == null) {
            return;
        }

        walkerNode(node.left, records);
        records.add(node.val);
        walkerNode(node.right, records);
    }


    public List<Integer> stackTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root == null) {
            return list;
        }
        Set<TreeNode> visited = new HashSet<>();
        stack.push(root);
        visited.add(root);
        while(!stack.isEmpty()) {
            TreeNode top = stack.peek();
            if (top.left != null && !visited.contains(top.left)) {
                stack.push(top.left);
                visited.add(top.left);
                continue;
            }
            list.add(top.val);
            stack.pop();
            if (top.right != null && !visited.contains(top.right)) {
                stack.push(top.right);
                visited.add(top.right);
            }
        }
        return list; 
    }


    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        root.left = node1;
        root.right = node2;

        List<Integer> list = new TreeMiddleFlow().stackTraversal(root);
        for(Integer integer : list) {
            System.out.println(integer);
        }
    }
}
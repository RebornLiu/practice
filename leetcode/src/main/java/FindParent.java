import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FindParent {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> pPath = new LinkedList<>();
        LinkedList<TreeNode> qPath = new LinkedList<>();
        findPath(root, p, pPath);
        findPath(root, q, qPath);

        int len = Math.min(pPath.size(), qPath.size());
        if (pPath.get(len - 1).val == qPath.get(len - 1).val) {
            return pPath.get(len - 1);
        }
        for (int i = 0; i < len; i ++) {
             if (pPath.get(i).val == qPath.get(i).val) {
                 continue;
             }

             return pPath.get(i - 1);
        }

        return root;
    }

    boolean findPath(TreeNode root, TreeNode target, LinkedList<TreeNode> path) {

        if (root == null) {
            return false;
        }
        if (root.val == target.val) {
            path.add(root);
            return true;
        }

        path.add(root);
        boolean findLeft = findPath(root.left, target, path);
        if (findLeft) {
            return true;
        }

        if (root.left != null) {
            path.removeLast();
        }
        boolean rightFind = findPath(root.right, target, path);
        if (rightFind) {
            return true;
        }

        if (root.right != null) {
            path.removeLast();
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        TreeNode n1 = new TreeNode(1);
        TreeNode n6 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2);
        TreeNode n0 = new TreeNode(0);
        TreeNode n8 = new TreeNode(8);
        TreeNode n7 = new TreeNode(7);
        TreeNode n4 = new TreeNode(4);

        root.left = n5;
        root.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        new FindParent().lowestCommonAncestor(root, n5, n4);

    }
}

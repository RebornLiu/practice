import sun.reflect.generics.tree.Tree;

import java.util.Stack;

public class MirrorTree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        /*Stack<Integer> left = new Stack<>();
        Stack<Integer> right = new Stack<>();
        walkLeft(root.left, left);
        walkRight(root.right, right);

        return eq(left, right);*/
        return eqTree(root.left, root.right);
    }


    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }

        Stack<Integer> left = new Stack<>();
        Stack<Integer> right = new Stack<>();
        walkLeft(root.left, left);
        walkRight(root.right, right);

        return eq(left, right);
    }


    private boolean eqTree(TreeNode left, TreeNode right) {

        if (left == null && right != null) {
            return false;
        }
        if (left != null && right == null) {
            return false;
        }

        if (left == null) {
            return true;
        }

        return left.val == right.val && eqTree(left.left, right.right) && eqTree(left.right, right.left);
    }


    private boolean eq(Stack<Integer> left, Stack<Integer> right) {
        while (!left.isEmpty() && !right.isEmpty()) {
            Integer leftVal = left.pop();
            Integer rightVal = right.pop();
            if (leftVal == null && rightVal == null) {
                continue;
            }

            if (leftVal != null && rightVal != null && leftVal.intValue() == rightVal) {
                continue;
            }

            return false;
        }

        if (!left.isEmpty()) {
            return false;
        }

        if (!right.isEmpty()) {
            return false;
        }

        return true;
    }

    private void walkLeft(TreeNode root, Stack<Integer> stack) {
        if (root == null) {
            return;
        }

        stack.push(root.left == null ? null : root.left.val);
        stack.push(root.right == null ? null : root.right.val);
        stack.push(root.val);
        walkLeft(root.left, stack);
        walkLeft(root.right, stack);
    }

    private void walkRight(TreeNode root, Stack<Integer> stack) {
        if (root == null) {
            return;
        }

        stack.push(root.right == null ? null : root.right.val);
        stack.push(root.left == null ? null : root.left.val);
        stack.push(root.val);
        walkRight(root.right, stack);
        walkRight(root.left, stack);
    }
}



class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

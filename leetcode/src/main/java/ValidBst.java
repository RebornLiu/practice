public class ValidBst {
    public boolean isValidBST(TreeNode root) {
        if(root == null) {
            return true;
        }

        boolean leftll = true;
        if(root.left != null) {
            int leftMax = maxNode(root.left);
            if(leftMax >= root.val) {
                return false;
            }
            else {
                 leftll = isValidBST(root.left);   
                 if(leftll == false) {
                     return false;
                 }
            }
        }

        boolean righttt = true;
        if(root.right != null) {
            int rightMin = minNode(root.right);
            if(rightMin <= root.val) {
                return false;
            }
            else {
                righttt = isValidBST(root.right);
                if(righttt == false) {
                    return false;
                }
            }
        }

        return true;
        
    }

    private Integer minNode(TreeNode root) {
        if(root.left == null && root.right == null) {
            return root.val;
        }

        if(root.left == null && root.right != null) {
            return Math.min(root.val, minNode(root.right));
        }

        if(root.left != null && root.right == null) {
            return Math.min(root.val, minNode(root.left));
        }

        return Math.min(root.val, Math.min(minNode(root.left), minNode(root.right)));
    }

    private Integer maxNode(TreeNode root) {
        if(root.left == null && root.right == null) {
            return root.val;
        }

        if(root.left == null && root.right != null) {
            return Math.max(root.val, maxNode(root.right));
        }

        if(root.left != null && root.right == null) {
            return Math.max(root.val, maxNode(root.left));
        }

        return Math.max(root.val, Math.max(maxNode(root.left), maxNode(root.right)));
        
    }

    public boolean isValidBalanceTree(TreeNode root) {
        return helper(root, null, null);
    }

    private boolean helper(TreeNode root, Integer lower, Integer higher) {
        if (root == null) {
            return true;
        }

        if (lower != null && root.val <= lower)
            return false;
        if (higher != null && root.val >= higher)
            return false;

        if (!helper(root.left, lower, root.val))
            return false;
        if (!helper(root.right, root.val, higher))
            return false;

        return true;
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node30 = new TreeNode(30);
        TreeNode node10 = new TreeNode(10);
        TreeNode node15 = new TreeNode(15);
        TreeNode node45 = new TreeNode(45);

        root.right = node30;
        node30.left = node10;
        node10.right = node15;
        node15.right = node45;

        boolean s = new ValidBst().isValidBST(root);
        System.out.println(s);
    }
}
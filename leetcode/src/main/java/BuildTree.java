public class BuildTree {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null) {
            return null;
        }

        if(preorder.length == 0) {
            return null;
        }

        return buildSubTree(preorder, 0, preorder.length, inorder, 0, inorder.length);


    }

    private TreeNode buildSubTree(int[] preorder, int preStart, int preLen, int[] inorder, int inStart, int inLen) {
        if(preLen == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int val = preorder[preStart];
        int i = 0;
        for(; i < inLen; i ++) {
            if(inorder[i + inStart] == val) {
                break;
            }
        }

        TreeNode left = null;
        if(i > 0) {
            left = buildSubTree(preorder, preStart + 1,  i, inorder, inStart, i);
        }

        TreeNode right = null;
        if(i < inLen) {
            right = buildSubTree(preorder, preStart + i + 1, preLen - i - 1, inorder, inStart + i + 1, inLen - i - 1);
        }

        root.right = right;
        root.left = left;
        return root;
    }
}
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelTree {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<TreeNode> slaveQueue = new LinkedList<>();
        queue.add(root);
        List<Integer> sub = new ArrayList<>();
        while(!queue.isEmpty()) {
            TreeNode cur = queue.pollFirst();
            sub.add(cur.val);
            if(cur.left != null) {
                slaveQueue.addLast(cur.left);
            }
            if(cur.right != null) {
                slaveQueue.addLast(cur.right);
            }

            if(queue.isEmpty()) {
                queue.addAll(slaveQueue);
                slaveQueue.clear();
                result.add(new ArrayList<>(sub));
                sub.clear();
            }
        }

        return result;
    }
}
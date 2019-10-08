import java.util.LinkedList;
import java.util.Queue;

public class ConnectLevel {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i ++) {
                Node cur = queue.poll();
                if(i != size - 1) {
                    cur.next = queue.peek();
                }
                
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }

        return root;
    }

    public Node connectRec(Node root) {
        if (root == null) {
            return null;
        }
        root.left.next = root.right;
        cR(root.left);
        cR(root.right);

        return root;
    }

    private void cR(Node node) {
        if (node.left == null) {
            return;
        }
        node.left.next = node.right;
        if (node.next == null) {
            node.right.next = null;
        }
        else {
            node.right.next = node.next.left;
        }
        cR(node.left);
        cR(node.right);
    }
}
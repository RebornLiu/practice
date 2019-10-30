import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CloneList {

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node nHead = new Node(head.val, null,null);
        map.put(head, nHead);

        Node next = head.next;
        Node pre = nHead;
        while(next != null) {
            Node node = new Node(next.val, null, null);
            map.put(next, node);
            node.next = null;
            node.random = null;
            pre.next = node;
            pre.random = null;
            pre = node;
            next = next.next;
        }


        for(Map.Entry<Node, Node> entry : map.entrySet()) {
            Node key = entry.getKey().random;
            if (key == null) {
                entry.getValue().random = null;
            }
            else {
                entry.getValue().random = map.get(entry.getKey().random);
            }
        }

        return nHead;
    }

    public Node copyRandomList2(Node head) {
       if (head == null) {
           return null;
       }

       Node pre = head;

       while (pre != null) {
           Node cur = new Node(pre.val, pre.next, null);
           pre.next = cur;
           pre = cur.next;
       }

       pre = head;
       while (pre != null) {
           pre.next.random = pre.random == null ? null : pre.random.next;
           pre = pre.next == null ? null : pre.next.next;
       }

       Node newHead = head.next;
       Node oNode = head;
       Node nNode = head.next;
       while (oNode != null) {
           oNode.next = oNode.next == null ? null : oNode.next.next;
           nNode.next = oNode.next == null? null : oNode.next.next;
           oNode = oNode.next;
           nNode = nNode.next;
       }

       return newHead;
    }


    static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    public static void main(String[] args) {
        CloneList.Node node1 = new CloneList.Node(1, null, null);
        CloneList.Node node2 = new CloneList.Node(2, null, null);
        //CloneList.Node node3 = new CloneList.Node(3, null, null);
        //CloneList.Node node4 = new CloneList.Node(4, null, null);
        node1.next = node2;
        node2.next = null;
        //node3.next = node4;

        node1.random = node2;
        node2.random = node2;

        CloneList cloneList = new CloneList();
        cloneList.copyRandomList2(node1);

    }
}

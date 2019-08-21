import java.util.List;

public class AddTwoList {

    public static void main(String[] args) {
        AddTwoList addTwoList = new AddTwoList();

        ListNode l1 = format(new int[]{2, 4 ,3, 1, 1});
        ListNode l2 = format(new int[]{5, 6, 4,7,9});

        sout(l1);
        sout(l2);
        ListNode listNode = addTwoList.addTwoNumbers(l1, l2);
        sout(listNode);
    }

    public static ListNode format(int[] list) {
        int length = list.length;
        ListNode header = new ListNode(list[0]);
        ListNode walker = header;
        for (int i = 1; i < length ;i ++) {
            ListNode listNode = new ListNode(list[i]);
            walker.next = listNode;
            walker = walker.next;
        }

        return header;
    }

    public static void sout(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.val + "-");
            listNode = listNode.next;
        }
        System.out.println();
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode header = new ListNode((l1.val + l2.val) % 10);
        ListNode tail = header;
        int step = (l1.val + l2.val) / 10;
        ListNode walker1 = l1.next;
        ListNode walker2 = l2.next;
        for (; walker1 != null && walker2 != null; ) {
            tail.next = new ListNode((walker1.val + walker2.val + step) % 10);
            step = (walker1.val + walker2.val + step) / 10;
            tail = tail.next;
            walker1 = walker1.next;
            walker2 = walker2.next;
        }

        while (walker1 != null) {
            tail.next = new ListNode((walker1.val + step) % 10);
            step = (walker1.val + step) / 10;
            tail = tail.next;
            walker1 = walker1.next;
        }

        while (walker2 != null) {
            tail.next = new ListNode((walker2.val + step) % 10);
            step = (walker2.val + step) / 10;
            tail = tail.next;
            walker2 = walker2.next;
        }

        if (step > 0) {
            tail.next = new ListNode(1);
        }

        return header;
    }

}


class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
}
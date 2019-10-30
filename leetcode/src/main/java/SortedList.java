public class SortedList {
    public ListNode sortList(ListNode head) {
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len ++;
            cur = cur.next;
        }
        if (len == 0) {
            return head;
        }
        return help(head, len);

    }

    private ListNode help(ListNode head, int len) {
        if (len == 1) {
            return head;
        }
        if (len == 2) {
            if (head.val < head.next.val) {
                return head;
            }
            else {
                ListNode h = head.next;
                head.next.next = head;
                head.next = null;
                return h;
            }
        }
        int mid = len / 2;

        ListNode cur = head;
        for (int i = 1; i < mid; i ++) {
            cur = cur.next;
        }
        ListNode aHead = cur.next;
        cur.next = null;
        ListNode before = help(head, mid);
        ListNode after = help(aHead, len - mid);

        return mergeList(before, after);

    }

    private ListNode mergeList(ListNode before, ListNode after) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (before != null && after != null) {
            if (before.val < after.val) {
                cur.next = before;
                before = before.next;
            }
            else {
                cur.next = after;
                after = after.next;
            }
            cur = cur.next;
        }

        if (before != null) {
            cur.next = before;
        }

        if (after != null) {
            cur.next = after;
        }

        return head.next;
    }
}

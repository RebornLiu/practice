import java.util.List;

public class MergeKList {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }

        int len = lists.length;
        if (len == 0) {
            return null;
        }

        return merge(lists, 0, len - 1);
    }


    private ListNode merge(ListNode[] list, int left, int right) {
        if (left > right) {
            return null;
        }
        if (left == right) {
            return list[left];
        }

        int mid = (left + right) / 2;
        ListNode listLeft = merge(list, left, mid);
        ListNode listRight = merge(list, mid + 1, right);

        return merge2(listLeft, listRight);
    }

    private ListNode merge2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode header = new ListNode(0);
        ListNode tail = header;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            }
            else {
                tail.next = l2;
                l2 = l2.next;
            }

            tail = tail.next;
        }

        if (l1 != null) {
            tail.next = l1;
        }

        if (l2 != null) {
            tail.next = l2;
        }

        return header.next;
    }


    public static void main(String[] args) {
        ListNode l1 = build(new int[]{1, 4, 5});
        ListNode l2 = build(new int[]{1,3,4});
        ListNode l3 = build(new int[]{2,6});

        ListNode[] lists = new ListNode[3];
        lists[0] = l1;
        lists[1] = l2;
        lists[2] = l3;

        MergeKList mergeKList = new MergeKList();
        ListNode listNode = mergeKList.mergeKLists(lists);
    }

    static ListNode build(int[] a) {
        ListNode header = new ListNode(a[0]);
        ListNode h = header;
        for (int i = 1; i < a.length; i++) {
            ListNode cur = new ListNode(a[i]);
            header.next = cur;
            header = header.next;
        }

        return h;
    }
}

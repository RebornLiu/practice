public class InsectionList {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int lenA = 0;
        ListNode a = headA;
        while (a != null) {
            lenA ++;
            a = a.next;
        }

        int lenB = 0;
        ListNode b = headB;
        while (b != null) {
            lenB ++;
            b = b.next;
        }

        ListNode longest = null;
        ListNode duan = null;
        if (lenA > lenB) {
            longest = headA;
            duan = headB;
        }
        else {
            longest = headB;
            duan = headA;
        }

        int diff = Math.abs(lenA - lenB);
        for (int i = 0; i < diff; i ++) {
            longest = longest.next;
        }

        while (longest != duan) {
            longest = longest.next;
            duan = duan.next;
        }

        return longest;
    }
}

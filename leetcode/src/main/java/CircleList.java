public class CircleList {

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        do {
            if (fast.next == null) {
                return false;
            }

            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        } while (fast != null);

        return false;
    }
}

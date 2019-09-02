public class ListLastN {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }

        if (n <= 0) {
            return head;
        }

        ListNode pre = head;
        ListNode post = head;

        for (int i = 0; i < n - 1; i ++) {
            post = post.next;
            if (post == null) {
                return null;
            }
        }

        if(post.next == null) {
            return head.next;
        }
        post = post.next;
        while (post.next != null) {
            pre = pre.next;
            post = post.next;
        }

        pre.next = pre.next.next;

        return head;

    }
}